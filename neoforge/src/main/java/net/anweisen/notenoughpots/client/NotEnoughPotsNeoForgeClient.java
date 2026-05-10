package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterNamedRenderTypesEvent;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@EventBusSubscriber(
  value = Dist.CLIENT,
  modid = NotEnoughPotsCommons.MOD_ID,
  bus = EventBusSubscriber.Bus.MOD
)
public class NotEnoughPotsNeoForgeClient {

  @SubscribeEvent
  public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
    BlockColors blockColors = event.getBlockColors();

    event.register(mimicBlockColor(blockColors, Blocks.SUGAR_CANE), NotEnoughPotsBlockType.POTTED_SUGAR_CANE.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.SHORT_GRASS), NotEnoughPotsBlockType.POTTED_SHORT_GRASS.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.TALL_GRASS), NotEnoughPotsBlockType.POTTED_TALL_GRASS.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.LARGE_FERN), NotEnoughPotsBlockType.POTTED_LARGE_FERN.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.VINE), NotEnoughPotsBlockType.POTTED_VINE.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.OAK_LEAVES), NotEnoughPotsBlockType.POTTED_OAK_LEAVES.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.SPRUCE_LEAVES), NotEnoughPotsBlockType.POTTED_SPRUCE_LEAVES.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.BIRCH_LEAVES), NotEnoughPotsBlockType.POTTED_BIRCH_LEAVES.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.JUNGLE_LEAVES), NotEnoughPotsBlockType.POTTED_JUNGLE_LEAVES.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.ACACIA_LEAVES), NotEnoughPotsBlockType.POTTED_ACACIA_LEAVES.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.DARK_OAK_LEAVES), NotEnoughPotsBlockType.POTTED_DARK_OAK_LEAVES.findBlock());
    // 1.17+ (AZALEA_LEAVES and FLOWERING_AZALEA_LEAVES are not tinted!)
    // 1.19+
    event.register(mimicBlockColor(blockColors, Blocks.MANGROVE_LEAVES), NotEnoughPotsBlockType.POTTED_MANGROVE_LEAVES.findBlock());
    // 1.20+
    event.register(mimicBlockColor(blockColors, Blocks.PINK_PETALS), NotEnoughPotsBlockType.POTTED_PINK_PETALS.findBlock());
    // 1.20.3+ (PALE_OAK_LEAVES are not tinted!)
    // 1.21.5+
    event.register(mimicBlockColor(blockColors, Blocks.BUSH), NotEnoughPotsBlockType.POTTED_BUSH.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.WILDFLOWERS), NotEnoughPotsBlockType.POTTED_WILDFLOWERS.findBlock());

    event.register(agedStemBlockColor(blockColors, Blocks.MELON_STEM, 5), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    event.register(agedStemBlockColor(blockColors, Blocks.PUMPKIN_STEM, 7), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());

    // 1.13+
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_KELP.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_SEAGRASS.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL_FAN.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL_FAN.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL_FAN.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL_FAN.findBlock());
    event.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL_FAN.findBlock());
  }

  @SubscribeEvent
  public static void onRegisterNamedRenderTypes(RegisterNamedRenderTypesEvent event) {
    // not required for neo forge
  }

  private static BlockColor mimicBlockColor(BlockColors colors, Block template) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.defaultBlockState(), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor agedStemBlockColor(BlockColors colors, Block template, int stage) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.defaultBlockState().setValue(StemBlock.AGE, stage), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor warmWaterBlockColor() {
    return (blockState, blockAndTintGetter, blockPos, i) -> NotEnoughPotsCommons.WARM_WATER_COLOR;
  }

}
