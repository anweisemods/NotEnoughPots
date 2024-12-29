package net.anweisen.notenoughpots;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public enum NotEnoughPotsBlockType {

  POTTED_SUGAR_CANE(Blocks.SUGAR_CANE),
  POTTED_SUNFLOWER(Blocks.SUNFLOWER),
  POTTED_ROSE_BUSH(Blocks.ROSE_BUSH),
  POTTED_PEONY(Blocks.PEONY),
  POTTED_LILAC(Blocks.LILAC),
  POTTED_LARGE_FERN(Blocks.LARGE_FERN),
  POTTED_MELON_STEM(Blocks.MELON_STEM),
  POTTED_PUMPKIN_STEM(Blocks.PUMPKIN_STEM),
  POTTED_SHORT_GRASS(Blocks.SHORT_GRASS),
  POTTED_TALL_GRASS(Blocks.TALL_GRASS),
  POTTED_WHEAT(Blocks.WHEAT),
  POTTED_CARROTS(Blocks.CARROTS),
  POTTED_POTATOES(Blocks.POTATOES),
  POTTED_MELON_BLOCK(Blocks.MELON),
  POTTED_PUMPKIN_BLOCK(Blocks.PUMPKIN),
  POTTED_CHORUS_FLOWER(Blocks.CHORUS_FLOWER),
  // 1.13+
  POTTED_SEA_PICKLE(Blocks.SEA_PICKLE),
  // 1.14+
  POTTED_SWEET_BERRY_BUSH(Blocks.SWEET_BERRY_BUSH),
  // 1.16+
  POTTED_TWISTING_VINES(Blocks.TWISTING_VINES),
  POTTED_WEEPING_VINES(Blocks.WEEPING_VINES),
  // 1.17+
  POTTED_CAVE_VINES(Blocks.CAVE_VINES), // (Glow Berries)
  POTTED_HANGING_ROOTS(Blocks.HANGING_ROOTS),
  ;

  private final String name;
  private final Block flower;

  NotEnoughPotsBlockType(Block flower) {
    this.flower = flower;
    this.name = this.name().toLowerCase();
  }

  public static Block flowerPot(Block flower) {
    // BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT) does no longer work in 1.21.2+
    return new FlowerPotBlock(flower, Blocks.FLOWER_POT.properties());
  }

  public static Block registerBuiltIn(String name, Block block) {
    return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
  }

  public Block getFlowerBlock() {
    return this.flower;
  }

  public String getName() {
    return this.name;
  }

  public Block findBlock() {
    return CommonClass.getBridge().getPottedBlock(this);
  }

  public ResourceLocation toResourceLocation() {
    return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, this.name);
  }

}
