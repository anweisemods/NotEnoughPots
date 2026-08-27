package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
// 1.13 port: MCP class names -- Blocks lives in net.minecraft.init here and StemBlock is BlockStem
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@Mod.EventBusSubscriber(
  value = Dist.CLIENT,
  modid = NotEnoughPotsCommons.MOD_ID,
  bus = Mod.EventBusSubscriber.Bus.MOD
)
public class NotEnoughPotsForgeClient {

  // 1.13 port: no render layer override -- the render layer is still a property of the block itself
  // here (Block#getRenderLayer), and vanilla BlockFlowerPot already returns CUTOUT.
  // RenderTypeLookup only arrives with the 1.15 render rewrite.

  @SubscribeEvent
  public static void onRegisterBlockColors(ColorHandlerEvent.Block event) {
    BlockColors blockColors = event.getBlockColors();

    blockColors.register(mimicBlockColor(blockColors, Blocks.SUGAR_CANE), NotEnoughPotsBlockType.POTTED_SUGAR_CANE.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.GRASS), NotEnoughPotsBlockType.POTTED_SHORT_GRASS.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.TALL_GRASS), NotEnoughPotsBlockType.POTTED_TALL_GRASS.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.LARGE_FERN), NotEnoughPotsBlockType.POTTED_LARGE_FERN.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.VINE), NotEnoughPotsBlockType.POTTED_VINE.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.OAK_LEAVES), NotEnoughPotsBlockType.POTTED_OAK_LEAVES.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.SPRUCE_LEAVES), NotEnoughPotsBlockType.POTTED_SPRUCE_LEAVES.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.BIRCH_LEAVES), NotEnoughPotsBlockType.POTTED_BIRCH_LEAVES.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.JUNGLE_LEAVES), NotEnoughPotsBlockType.POTTED_JUNGLE_LEAVES.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.ACACIA_LEAVES), NotEnoughPotsBlockType.POTTED_ACACIA_LEAVES.findBlock());
    blockColors.register(mimicBlockColor(blockColors, Blocks.DARK_OAK_LEAVES), NotEnoughPotsBlockType.POTTED_DARK_OAK_LEAVES.findBlock());

    blockColors.register(agedStemBlockColor(blockColors, Blocks.MELON_STEM, 5), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    blockColors.register(agedStemBlockColor(blockColors, Blocks.PUMPKIN_STEM, 7), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());

    // 1.13+
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_KELP.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_SEAGRASS.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL_FAN.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL_FAN.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL_FAN.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL_FAN.findBlock());
    blockColors.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL_FAN.findBlock());
  }

  private static IBlockColor mimicBlockColor(BlockColors colors, Block template) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.getDefaultState(), blockAndTintGetter, blockPos, i);
  }

  private static IBlockColor agedStemBlockColor(BlockColors colors, Block template, int stage) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.getDefaultState().with(BlockStem.AGE, stage), blockAndTintGetter, blockPos, i);
  }

  private static IBlockColor warmWaterBlockColor() {
    return (blockState, blockAndTintGetter, blockPos, i) -> NotEnoughPotsCommons.WARM_WATER_COLOR;
  }

}
