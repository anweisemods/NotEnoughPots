package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.ForgePlatformBridge;
// 1.14 port: Forge's 1.14.x mappings keep MCP class names (see forge/build.gradle: remapCommonToMcp)
import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(NotEnoughPotsCommons.MOD_ID)
public class NotEnoughPotsForgeMod {

  // 1.14 port: DeferredRegister has no static create(...) factory yet, only the public constructor
  public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NotEnoughPotsCommons.MOD_ID);

  public NotEnoughPotsForgeMod() {
    FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
    IEventBus eventBus = context.getModEventBus();
    ForgePlatformBridge<NotEnoughPotsBlockType> bridge = new ForgePlatformBridge<>(NotEnoughPotsCommons.MOD_ID, eventBus, BLOCKS, NotEnoughPotsBlockType.class);
    NotEnoughPotsCommons.init(bridge);
  }
}
