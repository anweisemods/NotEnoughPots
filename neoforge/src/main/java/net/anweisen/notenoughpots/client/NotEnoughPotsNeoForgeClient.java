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
    event.register(agedStemBlockColor(blockColors, Blocks.MELON_STEM, 5), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    event.register(agedStemBlockColor(blockColors, Blocks.PUMPKIN_STEM, 7), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());
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

}
