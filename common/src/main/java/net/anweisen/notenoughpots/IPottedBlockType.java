package net.anweisen.notenoughpots;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.Optional;

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
   * @see #getFlowerBlock()
   */
  default Block createPottedFlowerBlock(String modId) {
    // BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT) does no longer work in 1.21.2+
    // Apply loot table via custom properties (#6)
    return new FlowerPotBlock(this.getFlowerBlock(), createPottedFlowerBlockProperties(modId));
  }

  /**
   * Creates the block properties for the potted block.
   * Copied from vanilla FLOWER_POT properties.
   * Applies custom id, and flower block light level.
   *
   * @param modId The mod id to use for the resource location
   * @return The block properties for the potted block
   * @since 1.4.1
   */
  default BlockBehaviour.Properties createPottedFlowerBlockProperties(String modId) {
    // Copied from vanilla FLOWER_POT properties
    // (#6): no need to set loot_table manually [since 1.21: in "loot_table/blocks"]
    // PushReaction.DESTROY has been renamed to POPPED in 26.3
    return BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.POPPED)
      .lightLevel(state -> getLightEmission())
      .setId(ResourceKey.create(Registries.BLOCK, createResourceLocation(modId)));
  }

  /**
   * The light level the potted block emits.
   * Defaults to what the flower block emits in its default state, which is where almost
   * every block states its emission.
   * Override it for a flower block whose emission is gated on a block state property the
   * potted block does not have, and whose default state therefore reads as dark.
   *
   * @return The light level to emit, 0-15
   * @since 1.5.1
   * @see #getFlowerBlock()
   */
  default int getLightEmission() {
    return this.getFlowerBlock().defaultBlockState().getLightEmission();
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
  // ResourceLocation has been renamed to Identifier in 26.1
  default Identifier createResourceLocation(String modId) {
    return Identifier.fromNamespaceAndPath(modId, this.getName());
  }

}
