package net.anweisen.notenoughpots;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;

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
  default Block.Properties createPottedFlowerBlockProperties(String modId) {
    // (#6): no need to set loot_table manually [it defaults to "<mod_id>:blocks/<name>"],
    //      Properties.copy does not carry over the "drops" field of the vanilla flower pot
    // 1.15 port: no instabreak() here -- it is protected in vanilla and redundant anyway, copy()
    //      already carries the flower pot's zeroed hardness and blast resistance over
    // 1.15 port: light is a plain int on the properties -- the ToIntFunction<BlockState> overload
    //      only arrives in 1.16, so resolve the flower's emission eagerly. lightLevel(int) is
    //      protected in vanilla, see notenoughpots.accesswidener
    return Block.Properties.copy(Blocks.FLOWER_POT).noOcclusion()
      .lightLevel(getFlowerBlock().defaultBlockState().getLightEmission());
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
