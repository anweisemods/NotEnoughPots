package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.ArrayList;
import java.util.Collection;

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

  @SubscribeEvent
  public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
    BlockColors blockColors = event.getBlockColors();

    event.register(mimicBlockColor(blockColors, Blocks.SUGAR_CANE), NotEnoughPotsBlockType.POTTED_SUGAR_CANE.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.SHORT_GRASS), NotEnoughPotsBlockType.POTTED_SHORT_GRASS.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.TALL_GRASS), NotEnoughPotsBlockType.POTTED_TALL_GRASS.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.LARGE_FERN), NotEnoughPotsBlockType.POTTED_LARGE_FERN.findBlock());
    event.register(mimicBlockColor(blockColors, Blocks.BUSH), NotEnoughPotsBlockType.POTTED_BUSH.findBlock());
    event.register(agedStemBlockColor(blockColors, Blocks.MELON_STEM, 5), NotEnoughPotsBlockType.POTTED_MELON_STEM.findBlock());
    event.register(agedStemBlockColor(blockColors, Blocks.PUMPKIN_STEM, 7), NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM.findBlock());
  }

  @SubscribeEvent
  public static void onModelEvent(ModelEvent.ModifyBakingResult event) {
    Collection<BlockState> blockStates = new ArrayList<>();
    for (BlockState state : event.getResults().blockStateModels().keySet()) {
      if (state.getBlockHolder().getRegisteredName().startsWith(NotEnoughPotsCommons.MOD_ID + ResourceLocation.NAMESPACE_SEPARATOR)) {
        blockStates.add(state);
      }
    }

    // forge weirdly ignores the "render_type" (cutout) in the model json files -> replace it with an override
    // (recommended as a replacement for ItemBlockRenderTypes.setRenderLayer in the javadoc)
    for (BlockState state : blockStates) {
      event.getResults().blockStateModels().computeIfPresent(state, (loc, originalModel) -> new CutoutDelegateModel(originalModel));
    }
  }

  @SubscribeEvent
  @SuppressWarnings("deprecation")
  public static void onRegisterNamedRenderTypes(RegisterNamedRenderTypesEvent event) {
    for (NotEnoughPotsBlockType block : NotEnoughPotsBlockType.values()) {
      // does not work as expected:
      // event.register(block.getName(), RenderType.cutout(), Sheets.cutoutBlockSheet());

      // deprecated in minecraft (1.19) but not in forge?
      // (!) forge: marked for removal: 1.21.5
      // ItemBlockRenderTypes.setRenderLayer(block.findBlock(), RenderType.cutout());
    }
  }

  private static BlockColor mimicBlockColor(BlockColors colors, Block template) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.defaultBlockState(), blockAndTintGetter, blockPos, i);
  }

  private static BlockColor agedStemBlockColor(BlockColors colors, Block template, int stage) {
    return (blockState, blockAndTintGetter, blockPos, i) -> colors.getColor(template.defaultBlockState().setValue(StemBlock.AGE, stage), blockAndTintGetter, blockPos, i);
  }

}
