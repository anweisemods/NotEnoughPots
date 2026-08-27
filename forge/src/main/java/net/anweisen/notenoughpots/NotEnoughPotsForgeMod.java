package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.ForgePlatformBridge;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// 1.12 port: the old FML @Mod annotation -- useMetadata pulls name/version/description out of
// mcmod.info so the values stay in gradle.properties instead of being duplicated here
@Mod(modid = NotEnoughPotsCommons.MOD_ID, useMetadata = true, acceptedMinecraftVersions = "[1.12.2]")
@Mod.EventBusSubscriber(modid = NotEnoughPotsCommons.MOD_ID)
public class NotEnoughPotsForgeMod {

  private static ForgePlatformBridge<NotEnoughPotsBlockType> bridge;

  public NotEnoughPotsForgeMod() {
    bridge = new ForgePlatformBridge<>(NotEnoughPotsCommons.MOD_ID, NotEnoughPotsBlockType.class);
    NotEnoughPotsCommons.init(bridge);
  }

  // 1.12 port: no DeferredRegister and no separate mod event bus, the registry events are fired on
  // the normal forge bus that @Mod.EventBusSubscriber hooks into
  @SubscribeEvent
  public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
    bridge.registerAll(event.getRegistry());
  }

  /**
   * 1.12 port: the vanilla flower pot only accepts the fixed {@code BlockFlowerPot.EnumFlowerType}
   * set, and that enum cannot be extended. Right clicking an empty pot with one of our plants
   * therefore swaps the pot for the matching potted block from here. Taking the plant back out is
   * handled by the block itself, see {@link PottedBlock#onBlockActivated}.
   */
  @SubscribeEvent
  public static void onRightClickFlowerPot(PlayerInteractEvent.RightClickBlock event) {
    // a sneaking player places their block instead of using it, just like on a vanilla pot
    if (event.getEntityPlayer().isSneaking()) return;

    ItemStack held = event.getItemStack();
    NotEnoughPotsBlockType type = NotEnoughPotsBlockType.findByPlant(held);
    if (type == null) return;

    World world = event.getWorld();
    BlockPos pos = event.getPos();
    if (world.getBlockState(pos).getBlock() != Blocks.FLOWER_POT) return;

    // only an empty pot -- a pot that already holds a vanilla flower keeps its own behaviour
    TileEntity tile = world.getTileEntity(pos);
    if (!(tile instanceof TileEntityFlowerPot)) return;
    if (((TileEntityFlowerPot) tile).getFlowerPotItem() != Items.AIR) return;

    event.setCanceled(true);
    event.setCancellationResult(EnumActionResult.SUCCESS);
    if (world.isRemote) return;

    world.setBlockState(pos, type.findBlock().getDefaultState(), 3);
    if (!event.getEntityPlayer().capabilities.isCreativeMode) {
      held.shrink(1);
    }
  }
}
