package net.anweisen.notenoughpots.client;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsCommons;
// 1.12 port: MCP class names -- BlockColor is IBlockColor and its method is colorMultiplier here
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@Mod.EventBusSubscriber(
  value = Side.CLIENT,
  modid = NotEnoughPotsCommons.MOD_ID
)
public class NotEnoughPotsForgeClient {

  // 1.12 port: no render layer override here -- the layer is a property of the block itself
  // (Block#getBlockLayer), see PottedBlock. RenderTypeLookup only arrives with the 1.15 rewrite.

  @SubscribeEvent
  public static void onRegisterBlockColors(ColorHandlerEvent.Block event) {
    BlockColors blockColors = event.getBlockColors();

    // every plant whose own block is biome tinted has to be tinted in the pot as well
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_SUGAR_CANE);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_SHORT_GRASS);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_TALL_GRASS);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_LARGE_FERN);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_VINE);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_OAK_LEAVES);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_SPRUCE_LEAVES);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_BIRCH_LEAVES);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_JUNGLE_LEAVES);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_ACACIA_LEAVES);
    mimic(blockColors, NotEnoughPotsBlockType.POTTED_DARK_OAK_LEAVES);

    agedStem(blockColors, NotEnoughPotsBlockType.POTTED_MELON_STEM, 5);
    agedStem(blockColors, NotEnoughPotsBlockType.POTTED_PUMPKIN_STEM, 7);
  }

  private static void mimic(BlockColors colors, NotEnoughPotsBlockType type) {
    colors.registerBlockColorHandler(mimicBlockColor(colors, type.getFlowerState()), type.findBlock());
  }

  private static void agedStem(BlockColors colors, NotEnoughPotsBlockType type, int stage) {
    IBlockState grown = type.getFlowerState().withProperty(BlockStem.AGE, stage);
    colors.registerBlockColorHandler(mimicBlockColor(colors, grown), type.findBlock());
  }

  private static IBlockColor mimicBlockColor(BlockColors colors, IBlockState template) {
    return (blockState, blockAccess, blockPos, tintIndex) -> colors.colorMultiplier(template, blockAccess, blockPos, tintIndex);
  }

}
