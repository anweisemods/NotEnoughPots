package net.anweisen.notenoughpots.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import java.util.List;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.4
 */
public class CutoutDelegateModel implements BlockStateModel {

  private final BlockStateModel delegate;

  public CutoutDelegateModel(BlockStateModel delegate) {
    this.delegate = delegate;
  }

  @Override
  public void collectParts(RandomSource randomSource, List<BlockModelPart> list) {
    delegate.collectParts(randomSource, list);
  }

  @Override
  public TextureAtlasSprite particleIcon() {
    return delegate.particleIcon();
  }

  @Override
  public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
    return ChunkRenderTypeSet.of(RenderType.cutout());
  }

}
