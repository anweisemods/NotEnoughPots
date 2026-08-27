package net.anweisen.notenoughpots;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * A flower pot holding one plant.
 *
 * <p>1.12 port: before the flattening the vanilla flower pot is a single block backed by a
 * {@link net.minecraft.tileentity.TileEntityFlowerPot} whose contents are limited to the fixed
 * {@code BlockFlowerPot.EnumFlowerType} enum, which a mod cannot extend. So instead of subclassing
 * it, every potted plant is its own block here; the vanilla pot is swapped for it by the right
 * click handler in the loader module, and right clicking it again puts the empty pot back.
 *
 * @author anweisen | https://github.com/anweisen
 * @since 1.5
 */
public class PottedBlock extends Block {

  // same shape as vanilla BlockFlowerPot.FLOWER_POT_AABB
  protected static final AxisAlignedBB FLOWER_POT_AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);

  private final IPottedBlockType type;

  public PottedBlock(IPottedBlockType type) {
    super(Material.CIRCUITS);
    this.type = type;
    this.setHardness(0.0F);
    this.setSoundType(SoundType.STONE);
    // apply the flower's light level -- setLightLevel takes a 0..1 fraction of the 0..15 range here
    this.setLightLevel(type.getFlowerState().getLightValue() / 15.0F);
    this.setUnlocalizedName(type.getName());
  }

  public IPottedBlockType getPottedType() {
    return this.type;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return FLOWER_POT_AABB;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
    return BlockFaceShape.UNDEFINED;
  }

  @Override
  public BlockRenderLayer getBlockLayer() {
    // 1.12 port: the render layer is a property of the block itself, there is no RenderTypeLookup
    // and the "render_type" field in the model json does not exist yet either
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  public boolean canPlaceBlockAt(World world, BlockPos pos) {
    return super.canPlaceBlockAt(world, pos) && world.getBlockState(pos.down()).isTopSolid();
  }

  @Override
  public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
    // like a vanilla flower pot: pop off once the block below is gone
    if (!world.getBlockState(pos.down()).isTopSolid()) {
      this.dropBlockAsItem(world, pos, state, 0);
      world.setBlockToAir(pos);
    }
  }

  @Override
  public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    // 1.12 port: block loot tables only arrive in 1.14, the drops are spelled out here
    drops.add(new ItemStack(Items.FLOWER_POT));
    drops.add(this.type.createPlantStack());
  }

  @Override
  public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    return new ItemStack(Items.FLOWER_POT);
  }

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    // take the plant back out, exactly like right clicking a filled vanilla flower pot
    if (!world.isRemote) {
      ItemStack plant = this.type.createPlantStack();
      if (player.getHeldItem(hand).isEmpty()) {
        player.setHeldItem(hand, plant);
      } else if (!player.addItemStackToInventory(plant)) {
        player.dropItem(plant, false);
      }
      world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState(), 3);
    }
    return true;
  }

}
