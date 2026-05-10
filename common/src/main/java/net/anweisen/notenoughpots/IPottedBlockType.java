package net.anweisen.notenoughpots;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

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
    // Apply loot table via custom properties (#6)
    return new FlowerPotBlock(this.getFlowerBlock(), createPottedFlowerBlockProperties(modId));
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
  default BlockBehaviour.Properties createPottedFlowerBlockProperties(String modId) {
    // (#6): no need to set loot_table manually [for pre-1.21: in "loot_tables/blocks"]
    // 1.19 port: replace "drops" field of properties with custom loot table via reflection (since there is no public method to set it)
    //      there is no id setter but no need to set it explicitly
    return BlockBehaviour.Properties.copy(Blocks.FLOWER_POT).instabreak().noOcclusion()
      .lightLevel(state -> getFlowerBlock().defaultBlockState().getLightEmission());
  }

  /**
   * Creates a resource location for the potted block based on the mod id and the internal name.
   * Used for registering the block and its loot table.
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
