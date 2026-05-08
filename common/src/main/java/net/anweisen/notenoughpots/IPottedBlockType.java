package net.anweisen.notenoughpots;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
   * be written in snake case (lower-case letters seperated by underscores).
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
    // BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT) does no longer work in 1.21.2+
    // Apply loot table via custom properties (#6)
    return new FlowerPotBlock(this.getFlowerBlock(), createPottedFlowerBlockProperties(modId));
  }

  /**
   * Creates the block properties for the potted block.
   * Copied from vanilla FLOWER_POT properties, applies custom id and loot table.
   *
   * @param modId The mod id to use for the resource location
   * @return The block properties for the potted block
   * @since 1.4.1
   */
  default BlockBehaviour.Properties createPottedFlowerBlockProperties(String modId) {
    // Copied from vanilla FLOWER_POT properties
    return BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)
      .setId(ResourceKey.create(Registries.BLOCK, createResourceLocation(modId)))
      .overrideLootTable(Optional.of(ResourceKey.create(Registries.LOOT_TABLE, createResourceLocation(modId))));
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
    return ResourceLocation.fromNamespaceAndPath(modId, this.getName());
  }

}
