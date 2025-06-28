package net.anweisen.notenoughpots;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.Locale;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public enum NotEnoughPotsBlockType implements IPottedBlockType {

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
  POTTED_BEETROOTS(Blocks.BEETROOTS),
  POTTED_MELON_BLOCK(Blocks.MELON),
  POTTED_PUMPKIN_BLOCK(Blocks.PUMPKIN),
  POTTED_CHORUS_FLOWER(Blocks.CHORUS_FLOWER),
  POTTED_CHORUS_PLANT(Blocks.CHORUS_PLANT),
  POTTED_NETHER_WART(Blocks.NETHER_WART),
  POTTED_COCOA(Blocks.COCOA),
  // 1.13+
  POTTED_SEA_PICKLE(Blocks.SEA_PICKLE),
  // 1.14+
  POTTED_SWEET_BERRY_BUSH(Blocks.SWEET_BERRY_BUSH),
  // 1.16+
  POTTED_TWISTING_VINES(Blocks.TWISTING_VINES),
  POTTED_WEEPING_VINES(Blocks.WEEPING_VINES),
  POTTED_NETHER_SPROUTS(Blocks.NETHER_SPROUTS),
  // 1.17+
  POTTED_CAVE_VINES(Blocks.CAVE_VINES), // (Glow Berries)
  POTTED_HANGING_ROOTS(Blocks.HANGING_ROOTS),
  POTTED_SPORE_BLOSSOM(Blocks.SPORE_BLOSSOM), // 1.18?
  POTTED_SMALL_DRIPLEAF(Blocks.SMALL_DRIPLEAF),
  POTTED_BIG_DRIPLEAF(Blocks.BIG_DRIPLEAF),
  // 1.20+
  POTTED_PITCHER_PLANT(Blocks.PITCHER_PLANT),
  POTTED_PITCHER_CROP(Blocks.PITCHER_CROP),
  POTTED_TORCHFLOWER_SEEDS(Blocks.TORCHFLOWER_CROP),
  // 1.20.3+
  POTTED_PALE_HANGING_MOSS(Blocks.PALE_HANGING_MOSS),
  // 1.21.5+
  POTTED_SHORT_DRY_GRASS(Blocks.SHORT_DRY_GRASS),
  POTTED_TALL_DRY_GRASS(Blocks.TALL_DRY_GRASS),
  ;

  private final String name;
  private final Block flower;

  NotEnoughPotsBlockType(Block flower) {
    this.flower = flower;
    // using Locale.ROOT fixes https://github.com/anweisemods/NotEnoughPots/issues/4
    this.name = this.name().toLowerCase(Locale.ROOT);
  }

  @Override
  public Block getFlowerBlock() {
    return this.flower;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public Block findBlock() {
    return NotEnoughPotsCommons.getBridge().getPottedBlock(this);
  }

}
