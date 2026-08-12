package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class NotEnoughPotsFabricClient implements ClientModInitializer {

  // the amount of tint layers our block models index into ("tintindex" 0 and 1)
  private static final int TINT_LAYERS = 2;

  @Override
  public void onInitializeClient() {
    // since 1.21.6 blocks no longer belong to a single render layer (cutout);
    // the chunk section layer is computed per quad from the transparency of its texture (26.1),
    // there is no BlockRenderLayerMap in the fabric api anymore

    BlockColorRegistry.register(mimicBlockColor(Blocks.SUGAR_CANE), NotEnoughPotsBlockType.POTTED_SUGAR_CANE.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.SHORT_GRASS), NotEnoughPotsBlockType.POTTED_SHORT_GRASS.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.TALL_GRASS), NotEnoughPotsBlockType.POTTED_TALL_GRASS.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.LARGE_FERN), NotEnoughPotsBlockType.POTTED_LARGE_FERN.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.VINE), NotEnoughPotsBlockType.POTTED_VINE.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.OAK_LEAVES), NotEnoughPotsBlockType.POTTED_OAK_LEAVES.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.SPRUCE_LEAVES), NotEnoughPotsBlockType.POTTED_SPRUCE_LEAVES.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.BIRCH_LEAVES), NotEnoughPotsBlockType.POTTED_BIRCH_LEAVES.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.JUNGLE_LEAVES), NotEnoughPotsBlockType.POTTED_JUNGLE_LEAVES.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.ACACIA_LEAVES), NotEnoughPotsBlockType.POTTED_ACACIA_LEAVES.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.DARK_OAK_LEAVES), NotEnoughPotsBlockType.POTTED_DARK_OAK_LEAVES.findBlock());
    // 1.17+ (AZALEA_LEAVES and FLOWERING_AZALEA_LEAVES are not tinted!)
    // 1.19+
    BlockColorRegistry.register(mimicBlockColor(Blocks.MANGROVE_LEAVES), NotEnoughPotsBlockType.POTTED_MANGROVE_LEAVES.findBlock());
    // 1.20+
    BlockColorRegistry.register(mimicBlockColor(Blocks.PINK_PETALS), NotEnoughPotsBlockType.POTTED_PINK_PETALS.findBlock());
    // 1.20.3+ (PALE_OAK_LEAVES are not tinted!)
    // 1.21.5+
    BlockColorRegistry.register(mimicBlockColor(Blocks.BUSH), NotEnoughPotsBlockType.POTTED_BUSH.findBlock());
    BlockColorRegistry.register(mimicBlockColor(Blocks.WILDFLOWERS), NotEnoughPotsBlockType.POTTED_WILDFLOWERS.findBlock());

    BlockColorRegistry.register(agedStemBlockColor(Blocks.MELON_STEM, 5), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    BlockColorRegistry.register(agedStemBlockColor(Blocks.PUMPKIN_STEM, 7), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());

    // 1.13+
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_KELP.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_SEAGRASS.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_TUBE_CORAL_FAN.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BRAIN_CORAL_FAN.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_BUBBLE_CORAL_FAN.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_FIRE_CORAL_FAN.findBlock());
    BlockColorRegistry.register(warmWaterBlockColor(), NotEnoughPotsBlockType.POTTED_HORN_CORAL_FAN.findBlock());
  }

  private static List<BlockTintSource> mimicBlockColor(Block template) {
    return mimicBlockColor(template.defaultBlockState());
  }

  private static List<BlockTintSource> agedStemBlockColor(Block template, int stage) {
    return mimicBlockColor(template.defaultBlockState().setValue(StemBlock.AGE, stage));
  }

  /**
   * Delegates every tint layer to the tint sources vanilla registered for the flower block.
   * The color must always be resolved using the state of the flower, as our potted block does not
   * have the block state properties the vanilla tint sources read (eg. {@link StemBlock#AGE}).
   * The vanilla sources have to be looked up lazily, they are not registered yet while the client initializes.
   */
  private static List<BlockTintSource> mimicBlockColor(BlockState template) {
    List<BlockTintSource> layers = new ArrayList<>(TINT_LAYERS);
    for (int layer = 0; layer < TINT_LAYERS; layer++) {
      layers.add(mimicTintSource(template, layer));
    }
    return List.copyOf(layers);
  }

  private static BlockTintSource mimicTintSource(BlockState template, int layer) {
    return new BlockTintSource() {

      private BlockTintSource resolve() {
        return Minecraft.getInstance().getBlockColors().getTintSource(template, layer);
      }

      @Override
      public int color(BlockState state) {
        BlockTintSource source = resolve();
        return source == null ? -1 : source.color(template);
      }

      @Override
      public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        BlockTintSource source = resolve();
        return source == null ? -1 : source.colorInWorld(template, level, pos);
      }

      @Override
      public int colorAsTerrainParticle(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        BlockTintSource source = resolve();
        return source == null ? -1 : source.colorAsTerrainParticle(template, level, pos);
      }
    };
  }

  private static List<BlockTintSource> warmWaterBlockColor() {
    return List.of(BlockTintSources.constant(NotEnoughPotsCommons.WARM_WATER_COLOR));
  }

}
