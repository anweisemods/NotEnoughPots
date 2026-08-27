package net.anweisen.notenoughpots;

// 1.12 port: MCP class names, see IPottedBlockType
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.Locale;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public enum NotEnoughPotsBlockType implements IPottedBlockType {

  // 1.12 port: the plant item is only spelled out where it is not the flower block's own item --
  // crops, reeds and cocoa have no block item and would otherwise be unobtainable
  POTTED_SUGAR_CANE(Blocks.REEDS, Items.REEDS),
  POTTED_SUNFLOWER(Blocks.DOUBLE_PLANT, BlockDoublePlant.EnumPlantType.SUNFLOWER.getMeta()),
  POTTED_ROSE_BUSH(Blocks.DOUBLE_PLANT, BlockDoublePlant.EnumPlantType.ROSE.getMeta()),
  POTTED_PEONY(Blocks.DOUBLE_PLANT, BlockDoublePlant.EnumPlantType.PAEONIA.getMeta()),
  POTTED_LILAC(Blocks.DOUBLE_PLANT, BlockDoublePlant.EnumPlantType.SYRINGA.getMeta()),
  POTTED_LARGE_FERN(Blocks.DOUBLE_PLANT, BlockDoublePlant.EnumPlantType.FERN.getMeta()),
  POTTED_MELON_STEM(Blocks.MELON_STEM, Items.MELON_SEEDS),
  POTTED_PUMPKIN_STEM(Blocks.PUMPKIN_STEM, Items.PUMPKIN_SEEDS),
  POTTED_SHORT_GRASS(Blocks.TALLGRASS, BlockTallGrass.EnumType.GRASS.getMeta()),
  POTTED_TALL_GRASS(Blocks.DOUBLE_PLANT, BlockDoublePlant.EnumPlantType.GRASS.getMeta()),
  POTTED_WHEAT(Blocks.WHEAT, Items.WHEAT_SEEDS),
  POTTED_CARROTS(Blocks.CARROTS, Items.CARROT),
  POTTED_POTATOES(Blocks.POTATOES, Items.POTATO),
  POTTED_BEETROOTS(Blocks.BEETROOTS, Items.BEETROOT_SEEDS),
  POTTED_MELON_BLOCK(Blocks.MELON_BLOCK),
  // 1.12 port: minecraft:pumpkin still is the carved pumpkin here, there is no separate
  //      carved_pumpkin block -- so this branch has no POTTED_CARVED_PUMPKIN
  POTTED_PUMPKIN_BLOCK(Blocks.PUMPKIN),
  POTTED_CHORUS_FLOWER(Blocks.CHORUS_FLOWER),
  POTTED_CHORUS_PLANT(Blocks.CHORUS_PLANT),
  POTTED_NETHER_WART(Blocks.NETHER_WART, Items.NETHER_WART),
  POTTED_COCOA(Blocks.COCOA, new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage())),
  POTTED_VINE(Blocks.VINE),
  POTTED_RED_MUSHROOM_BLOCK(Blocks.RED_MUSHROOM_BLOCK),
  POTTED_BROWN_MUSHROOM_BLOCK(Blocks.BROWN_MUSHROOM_BLOCK),
  // 1.12 port: no POTTED_MUSHROOM_STEM -- the stem is only a metadata variant of the mushroom
  //      blocks here and has no item of its own that could be put into a pot
  POTTED_OAK_LEAVES(Blocks.LEAVES, BlockPlanks.EnumType.OAK.getMetadata()),
  POTTED_SPRUCE_LEAVES(Blocks.LEAVES, BlockPlanks.EnumType.SPRUCE.getMetadata()),
  POTTED_BIRCH_LEAVES(Blocks.LEAVES, BlockPlanks.EnumType.BIRCH.getMetadata()),
  POTTED_JUNGLE_LEAVES(Blocks.LEAVES, BlockPlanks.EnumType.JUNGLE.getMetadata()),
  // LEAVES2 holds the two wood types that did not fit into the metadata bits of LEAVES
  POTTED_ACACIA_LEAVES(Blocks.LEAVES2, BlockPlanks.EnumType.ACACIA.getMetadata() - 4),
  POTTED_DARK_OAK_LEAVES(Blocks.LEAVES2, BlockPlanks.EnumType.DARK_OAK.getMetadata() - 4),
  POTTED_NETHER_WART_BLOCK(Blocks.NETHER_WART_BLOCK),
  POTTED_HAY_BLOCK(Blocks.HAY_BLOCK),
  POTTED_JACK_O_LANTERN(Blocks.LIT_PUMPKIN),
  ;

  private final String name;
  private final Block flower;
  private final int meta;
  private final ItemStack plant;

  NotEnoughPotsBlockType(Block flower) {
    this(flower, 0, null);
  }

  NotEnoughPotsBlockType(Block flower, int meta) {
    this(flower, meta, null);
  }

  NotEnoughPotsBlockType(Block flower, Item plant) {
    this(flower, 0, new ItemStack(plant));
  }

  NotEnoughPotsBlockType(Block flower, ItemStack plant) {
    this(flower, 0, plant);
  }

  NotEnoughPotsBlockType(Block flower, int meta, ItemStack plant) {
    this.flower = flower;
    this.meta = meta;
    this.plant = plant != null ? plant : new ItemStack(flower, 1, meta);
    // using Locale.ROOT fixes https://github.com/anweisemods/NotEnoughPots/issues/4
    this.name = this.name().toLowerCase(Locale.ROOT);
  }

  @Override
  public Block getFlowerBlock() {
    return this.flower;
  }

  @Override
  public int getFlowerMeta() {
    return this.meta;
  }

  @Override
  public ItemStack createPlantStack() {
    return this.plant.copy();
  }

  @Override
  public String getName() {
    return this.name;
  }

  public Block findBlock() {
    return NotEnoughPotsCommons.getBridge().getPottedBlock(this);
  }

  /**
   * Looks up the potted block type an item stack can be potted as.
   *
   * @param stack The stack the player is holding
   * @return The matching type, or {@code null} if the item is not one of ours
   * @since 1.5
   */
  public static NotEnoughPotsBlockType findByPlant(ItemStack stack) {
    if (stack.isEmpty()) return null;
    for (NotEnoughPotsBlockType type : values()) {
      if (ItemStack.areItemsEqual(type.plant, stack)) return type;
    }
    return null;
  }

}
