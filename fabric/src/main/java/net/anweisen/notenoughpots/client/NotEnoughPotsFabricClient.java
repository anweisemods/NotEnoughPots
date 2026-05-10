package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
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
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.VINE), NotEnoughPotsBlockType.POTTED_VINE.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.OAK_LEAVES), NotEnoughPotsBlockType.POTTED_OAK_LEAVES.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.SPRUCE_LEAVES), NotEnoughPotsBlockType.POTTED_SPRUCE_LEAVES.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.BIRCH_LEAVES), NotEnoughPotsBlockType.POTTED_BIRCH_LEAVES.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.JUNGLE_LEAVES), NotEnoughPotsBlockType.POTTED_JUNGLE_LEAVES.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.ACACIA_LEAVES), NotEnoughPotsBlockType.POTTED_ACACIA_LEAVES.findBlock());
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.DARK_OAK_LEAVES), NotEnoughPotsBlockType.POTTED_DARK_OAK_LEAVES.findBlock());
    // 1.17+ (AZALEA_LEAVES and FLOWERING_AZALEA_LEAVES are not tinted!)
    // 1.19+
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.MANGROVE_LEAVES), NotEnoughPotsBlockType.POTTED_MANGROVE_LEAVES.findBlock());
    // 1.20+
    ColorProviderRegistry.BLOCK.register(mimicBlockColor(Blocks.PINK_PETALS), NotEnoughPotsBlockType.POTTED_PINK_PETALS.findBlock());

    ColorProviderRegistry.BLOCK.register(agedStemBlockColor(Blocks.MELON_STEM, 5), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    ColorProviderRegistry.BLOCK.register(agedStemBlockColor(Blocks.PUMPKIN_STEM, 7), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());

    // 1.13+
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_KELP.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_SEAGRASS.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL_FAN.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL_FAN.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL_FAN.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL_FAN.findBlock());
    ColorProviderRegistry.BLOCK.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL_FAN.findBlock());
  }

  private static BlockColor mimicBlockColor(Block block) {
    return (blockState, blockAndTintGetter, blockPos, i) -> ColorProviderRegistry.BLOCK.get(block).getColor(block.defaultBlockState(), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor agedStemBlockColor(Block block, int stage) {
    return (blockState, blockAndTintGetter, blockPos, i) -> ColorProviderRegistry.BLOCK.get(block).getColor(block.defaultBlockState().setValue(StemBlock.AGE, stage), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor warmWaterBlockColor() {
    return (blockState, blockAndTintGetter, blockPos, i) -> NotEnoughPotsCommons.WARM_WATER_COLOR;
  }

}
