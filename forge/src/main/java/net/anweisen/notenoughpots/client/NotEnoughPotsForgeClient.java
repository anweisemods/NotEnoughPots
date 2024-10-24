package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.Constants;
import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NotEnoughPotsForgeClient {

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

  private static BlockColor mimicBlockColor(BlockColors colors, Block template) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.defaultBlockState(), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor agedStemBlockColor(BlockColors colors, Block template, int stage) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.defaultBlockState().setValue(StemBlock.AGE, stage), blockAndTintGetter, blockPos, i);
  }

}
