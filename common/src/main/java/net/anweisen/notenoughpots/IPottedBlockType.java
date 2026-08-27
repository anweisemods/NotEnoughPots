package net.anweisen.notenoughpots;

// 1.13 port: there is no Fabric for 1.13, so :common is written directly in the MCP names Forge
// uses here -- no mojmap -> MCP translation step and no access widener any more
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.3
 *
 * @see net.anweisen.notenoughpots.platform.IPlatformBridge
 * @see net.anweisen.notenoughpots.platform.IPlatformBridge#registerPottedBlock(IPottedBlockType)
 */
public interface IPottedBlockType {

  /**
   * Returns the internal name (id) of the potted block,
   * under which it will be registered.
   * It should be unique and not contain any special characters and
   * be written in snake case (lower-case letters separated by underscores).
   *
   * @return The internal name (id) of the potted block
   */
  String getName();

  /**
   * @return The flower (block) that should be potted (inside the flower pot)
   */
  Block getFlowerBlock();

  /**
   * Creates a flowerpot block (the potted version) for the corresponding flower block.
   *
   * @return The potted block (flower pot)
   *
   * @see #getFlowerBlock()
   */
  default Block createPottedFlowerBlock(String modId) {
    // 1.13 port: BlockFlowerPot, the class is only renamed to FlowerPotBlock in the 1.14 mappings.
    // It drops the pot plus an ItemStack of its content itself (block loot tables only exist from
    // 1.14 on) -- which is empty for plants without a block item, hence PottedCropBlock.
    Block.Properties properties = createPottedFlowerBlockProperties(modId);
    Item crop = this.getCropItem();
    return crop == null
      ? new BlockFlowerPot(this.getFlowerBlock(), properties)
      : new PottedCropBlock(this.getFlowerBlock(), crop, properties);
  }

  /**
   * The item this potted block should drop for its plant, for plants that have no block item
   * (wheat, carrots, nether wart, ...) and would otherwise drop nothing but the pot.
   *
   * <p>1.13 port: from 1.14 on this is expressed by the block loot tables instead.
   *
   * @return The plant item to drop, or {@code null} to drop the flower block itself
   * @since 1.5
   */
  default Item getCropItem() {
    return null;
  }

  /**
   * Creates the block properties for the potted block.
   * Copied from vanilla FLOWER_POT properties.
   * Applies flower block light level.
   *
   * @param modId The mod id to use for the resource location
   * @return The block properties for the potted block
   * @since 1.4.1
   */
  default Block.Properties createPottedFlowerBlockProperties(String modId) {
    // 1.13 port: from() instead of copy(), lightValue(int) instead of lightLevel(...). No
    //      zeroHardnessAndResistance() either, from(FLOWER_POT) already carries the zeroed
    //      hardness and blast resistance over.
    return Block.Properties.from(Blocks.FLOWER_POT)
      .lightValue(getFlowerBlock().getDefaultState().getLightValue());
  }

  /**
   * Creates a resource location for the potted block based on the mod id and the internal name.
   * Used for registering the block.
   *
   * @param modId The mod id to use for the resource location
   * @return The created resource location
   * @since 1.4.1
   * @see #getName()
   */
  default ResourceLocation createResourceLocation(String modId) {
    return new ResourceLocation(modId, this.getName());
  }

}
