package net.anweisen.notenoughpots;

// 1.13 port: MCP class names, see IPottedBlockType
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
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
  POTTED_MELON_STEM(Blocks.MELON_STEM, Items.MELON_SEEDS),
  POTTED_PUMPKIN_STEM(Blocks.PUMPKIN_STEM, Items.PUMPKIN_SEEDS),
  POTTED_SHORT_GRASS(Blocks.GRASS),
  POTTED_TALL_GRASS(Blocks.TALL_GRASS),
  POTTED_WHEAT(Blocks.WHEAT, Items.WHEAT_SEEDS),
  POTTED_CARROTS(Blocks.CARROTS, Items.CARROT),
  POTTED_POTATOES(Blocks.POTATOES, Items.POTATO),
  POTTED_BEETROOTS(Blocks.BEETROOTS, Items.BEETROOT_SEEDS),
  POTTED_MELON_BLOCK(Blocks.MELON),
  POTTED_PUMPKIN_BLOCK(Blocks.PUMPKIN),
  POTTED_CHORUS_FLOWER(Blocks.CHORUS_FLOWER),
  POTTED_CHORUS_PLANT(Blocks.CHORUS_PLANT),
  POTTED_NETHER_WART(Blocks.NETHER_WART, Items.NETHER_WART),
  POTTED_COCOA(Blocks.COCOA, Items.COCOA_BEANS),
  POTTED_VINE(Blocks.VINE),
  POTTED_RED_MUSHROOM_BLOCK(Blocks.RED_MUSHROOM_BLOCK),
  POTTED_BROWN_MUSHROOM_BLOCK(Blocks.BROWN_MUSHROOM_BLOCK),
  POTTED_MUSHROOM_STEM(Blocks.MUSHROOM_STEM),
  POTTED_OAK_LEAVES(Blocks.OAK_LEAVES),
  POTTED_SPRUCE_LEAVES(Blocks.SPRUCE_LEAVES),
  POTTED_BIRCH_LEAVES(Blocks.BIRCH_LEAVES),
  POTTED_JUNGLE_LEAVES(Blocks.JUNGLE_LEAVES),
  POTTED_ACACIA_LEAVES(Blocks.ACACIA_LEAVES),
  POTTED_DARK_OAK_LEAVES(Blocks.DARK_OAK_LEAVES),
  POTTED_NETHER_WART_BLOCK(Blocks.NETHER_WART_BLOCK),
  POTTED_HAY_BLOCK(Blocks.HAY_BLOCK),
  POTTED_CARVED_PUMPKIN(Blocks.CARVED_PUMPKIN),
  POTTED_JACK_O_LANTERN(Blocks.JACK_O_LANTERN),
  // 1.13+
  POTTED_SEA_PICKLE(Blocks.SEA_PICKLE),
  POTTED_KELP(Blocks.KELP),
  POTTED_SEAGRASS(Blocks.SEAGRASS),
  POTTED_TUBE_CORAL(Blocks.TUBE_CORAL),
  POTTED_BRAIN_CORAL(Blocks.BRAIN_CORAL),
  POTTED_BUBBLE_CORAL(Blocks.BUBBLE_CORAL),
  POTTED_FIRE_CORAL(Blocks.FIRE_CORAL),
  POTTED_HORN_CORAL(Blocks.HORN_CORAL),
  POTTED_DEAD_TUBE_CORAL(Blocks.DEAD_TUBE_CORAL),
  POTTED_DEAD_BRAIN_CORAL(Blocks.DEAD_BRAIN_CORAL),
  POTTED_DEAD_BUBBLE_CORAL(Blocks.DEAD_BUBBLE_CORAL),
  POTTED_DEAD_FIRE_CORAL(Blocks.DEAD_FIRE_CORAL),
  POTTED_DEAD_HORN_CORAL(Blocks.DEAD_HORN_CORAL),
  POTTED_TUBE_CORAL_FAN(Blocks.TUBE_CORAL_FAN),
  POTTED_BRAIN_CORAL_FAN(Blocks.BRAIN_CORAL_FAN),
  POTTED_BUBBLE_CORAL_FAN(Blocks.BUBBLE_CORAL_FAN),
  POTTED_HORN_CORAL_FAN(Blocks.HORN_CORAL_FAN),
  POTTED_FIRE_CORAL_FAN(Blocks.FIRE_CORAL_FAN),
  POTTED_DEAD_TUBE_CORAL_FAN(Blocks.DEAD_TUBE_CORAL_FAN),
  POTTED_DEAD_BRAIN_CORAL_FAN(Blocks.DEAD_BRAIN_CORAL_FAN),
  POTTED_DEAD_BUBBLE_CORAL_FAN(Blocks.DEAD_BUBBLE_CORAL_FAN),
  POTTED_DEAD_HORN_CORAL_FAN(Blocks.DEAD_HORN_CORAL_FAN),
  POTTED_DEAD_FIRE_CORAL_FAN(Blocks.DEAD_FIRE_CORAL_FAN),
  POTTED_TUBE_CORAL_BLOCK(Blocks.TUBE_CORAL_BLOCK),
  POTTED_BRAIN_CORAL_BLOCK(Blocks.BRAIN_CORAL_BLOCK),
  POTTED_BUBBLE_CORAL_BLOCK(Blocks.BUBBLE_CORAL_BLOCK),
  POTTED_FIRE_CORAL_BLOCK(Blocks.FIRE_CORAL_BLOCK),
  POTTED_HORN_CORAL_BLOCK(Blocks.HORN_CORAL_BLOCK),
  POTTED_DEAD_TUBE_CORAL_BLOCK(Blocks.DEAD_TUBE_CORAL_BLOCK),
  POTTED_DEAD_BRAIN_CORAL_BLOCK(Blocks.DEAD_BRAIN_CORAL_BLOCK),
  POTTED_DEAD_BUBBLE_CORAL_BLOCK(Blocks.DEAD_BUBBLE_CORAL_BLOCK),
  POTTED_DEAD_FIRE_CORAL_BLOCK(Blocks.DEAD_FIRE_CORAL_BLOCK),
  POTTED_DEAD_HORN_CORAL_BLOCK(Blocks.DEAD_HORN_CORAL_BLOCK),
  ;

  private final String name;
  private final Block flower;
  // 1.13 port: the plant item to drop for plants that have no block item, see IPottedBlockType#getCropItem
  private final Item crop;

  NotEnoughPotsBlockType(Block flower) {
    this(flower, null);
  }

  NotEnoughPotsBlockType(Block flower, Item crop) {
    this.flower = flower;
    this.crop = crop;
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

  @Override
  public Item getCropItem() {
    return this.crop;
  }

  public Block findBlock() {
    return NotEnoughPotsCommons.getBridge().getPottedBlock(this);
  }

}
