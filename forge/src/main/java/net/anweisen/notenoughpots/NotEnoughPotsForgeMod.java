package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.ForgePlatformBridge;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(NotEnoughPotsCommons.MOD_ID)
@Mod.EventBusSubscriber(modid = NotEnoughPotsCommons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NotEnoughPotsForgeMod {

  private static ForgePlatformBridge<NotEnoughPotsBlockType> bridge;

  public NotEnoughPotsForgeMod() {
    bridge = new ForgePlatformBridge<>(NotEnoughPotsCommons.MOD_ID, NotEnoughPotsBlockType.class);
    NotEnoughPotsCommons.init(bridge);
  }

  // 1.13 port: DeferredRegister only arrives with forge 28 (1.14.4), so the blocks are built in the
  // mod constructor and handed to the registry event here
  @SubscribeEvent
  public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
    bridge.registerAll(event.getRegistry());
  }
}
