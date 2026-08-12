package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@EventBusSubscriber(
  value = Dist.CLIENT,
  modid = NotEnoughPotsCommons.MOD_ID
)
public class NotEnoughPotsNeoForgeClient {

  // the amount of tint layers our block models index into ("tintindex" 0 and 1)
  private static final int TINT_LAYERS = 2;

  // since 1.21.6 blocks no longer belong to a single render layer (cutout);
  // the chunk section layer is computed per quad from the transparency of its texture (26.1),
  // RegisterNamedRenderTypesEvent does not exist on neo forge anymore

  @SubscribeEvent
  public static void onRegisterBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
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

  private static List<BlockTintSource> mimicBlockColor(BlockColors colors, Block template) {
    return mimicBlockColor(colors, template.defaultBlockState());
  }

  private static List<BlockTintSource> agedStemBlockColor(BlockColors colors, Block template, int stage) {
    return mimicBlockColor(colors, template.defaultBlockState().setValue(StemBlock.AGE, stage));
  }

  /**
   * Delegates every tint layer to the tint sources registered for the flower block.
   * The color must always be resolved using the state of the flower, as our potted block does not
   * have the block state properties the vanilla tint sources read (eg. {@link StemBlock#AGE}).
   */
  private static List<BlockTintSource> mimicBlockColor(BlockColors colors, BlockState template) {
    List<BlockTintSource> layers = new ArrayList<>(TINT_LAYERS);
    for (int layer = 0; layer < TINT_LAYERS; layer++) {
      layers.add(mimicTintSource(colors, template, layer));
    }
    return List.copyOf(layers);
  }

  private static BlockTintSource mimicTintSource(BlockColors colors, BlockState template, int layer) {
    return new BlockTintSource() {

      private BlockTintSource resolve() {
        return colors.getTintSource(template, layer);
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
