package net.anweisen.notenoughpots;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A potted block for plants that have no block item of their own.
 *
 * <p>1.13 port: block drops are still hardcoded here, loot tables for blocks only arrive in 1.14.
 * {@link BlockFlowerPot} drops the pot plus {@code new ItemStack(content)}, which is empty for crops
 * like wheat or nether wart, so the seed/produce item the 1.14+ loot tables use is dropped instead.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.5
 */
public class PottedCropBlock extends BlockFlowerPot {

  private final Item crop;

  public PottedCropBlock(Block flower, Item crop, Block.Properties properties) {
    super(flower, properties);
    this.crop = crop;
  }

  @Override
  public void dropBlockAsItemWithChance(IBlockState state, World world, BlockPos pos, float chance, int fortune) {
    // drops the flower pot (and, for a block that has one, its content item)
    super.dropBlockAsItemWithChance(state, world, pos, chance, fortune);
    // BlockFlowerPot ignores the explosion chance for its content as well, so do the same here.
    // spawnAsEntity itself checks isRemote, doTileDrops and the stack being non-empty.
    spawnAsEntity(world, pos, new ItemStack(this.crop));
  }

  public Item getCropItem() {
    return this.crop;
  }

}
