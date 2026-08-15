package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.ForgePlatformBridge;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(NotEnoughPotsCommons.MOD_ID)
public class NotEnoughPotsForgeMod {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NotEnoughPotsCommons.MOD_ID);

  public NotEnoughPotsForgeMod() {
    FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
    IEventBus eventBus = context.getModEventBus();
    ForgePlatformBridge<NotEnoughPotsBlockType> bridge = new ForgePlatformBridge<>(NotEnoughPotsCommons.MOD_ID, eventBus, BLOCKS, NotEnoughPotsBlockType.class);
    NotEnoughPotsCommons.init(bridge);
  }
}
