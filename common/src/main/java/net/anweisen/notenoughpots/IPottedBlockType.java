package net.anweisen.notenoughpots;

// 1.12 port: there is no Fabric for 1.12, so :common is written directly in the MCP names Forge
// uses here -- no mojmap -> MCP translation step and no access widener
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
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
   * 1.12 port: before the flattening a lot of plants are metadata variants of a shared block
   * (leaves, double plants, tall grass), so the block alone does not identify the plant.
   *
   * @return The metadata of the flower block variant that should be potted
   * @since 1.5
   */
  int getFlowerMeta();

  /**
   * @return The block state of the plant, used for its light level and its block colour
   */
  default IBlockState getFlowerState() {
    return this.getFlowerBlock().getStateFromMeta(this.getFlowerMeta());
  }

  /**
   * The item stack of the plant: what has to be right clicked onto an empty flower pot to get this
   * potted block, and what the potted block gives back when it is broken or emptied again.
   *
   * <p>1.12 port: block loot tables only arrive in 1.14, so this is the drop as well.
   *
   * @return A fresh stack of the plant item
   * @since 1.5
   */
  ItemStack createPlantStack();

  /**
   * Creates a flowerpot block (the potted version) for the corresponding flower block.
   *
   * @return The potted block (flower pot)
   *
   * @see #getFlowerBlock()
   */
  default Block createPottedFlowerBlock(String modId) {
    // 1.12 port: BlockFlowerPot cannot be extended usefully here, see PottedBlock
    return new PottedBlock(this);
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
