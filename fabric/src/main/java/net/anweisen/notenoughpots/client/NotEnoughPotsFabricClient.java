package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class NotEnoughPotsFabricClient implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    for (NotEnoughPotsBlockType block : NotEnoughPotsBlockType.values()) {
      BlockRenderLayerMap.INSTANCE.putBlock(block.findBlock(), RenderType.cutout());
    }
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.SUGAR_CANE), NotEnoughPotsBlockType.POTTED_SUGAR_CANE.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.SHORT_GRASS), NotEnoughPotsBlockType.POTTED_SHORT_GRASS.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.TALL_GRASS), NotEnoughPotsBlockType.POTTED_TALL_GRASS.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.LARGE_FERN), NotEnoughPotsBlockType.POTTED_LARGE_FERN.findBlock());
    ColorProviderRegistry.BLOCK.register(agedStemBlockColor(Blocks.MELON_STEM, 4), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    ColorProviderRegistry.BLOCK.register(agedStemBlockColor(Blocks.PUMPKIN_STEM, 6), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());
  }

  private static BlockColor mimicBlockColor(Block block) {
    return (blockState, blockAndTintGetter, blockPos, i) -> ColorProviderRegistry.BLOCK.get(block).getColor(block.defaultBlockState(), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor agedStemBlockColor(Block block, int stage) {
    return (blockState, blockAndTintGetter, blockPos, i) -> ColorProviderRegistry.BLOCK.get(block).getColor(block.defaultBlockState().setValue(StemBlock.AGE, stage), blockAndTintGetter, blockPos, i);
  }

}
