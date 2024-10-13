package net.anweisen.notenoughpots;


import net.anweisen.notenoughpots.platform.NeoForgePlatformBridge;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Constants.MOD_ID)
public class NotEnoughPotsNeoForgeMod {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Constants.MOD_ID);

  public NotEnoughPotsNeoForgeMod(IEventBus eventBus) {
    var bridge = new NeoForgePlatformBridge(eventBus);
    CommonClass.init(bridge);
  }
}
