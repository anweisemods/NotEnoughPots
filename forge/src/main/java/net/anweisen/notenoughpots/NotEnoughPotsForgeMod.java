package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.ForgePlatformBridge;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(NotEnoughPotsCommons.MOD_ID)
public class NotEnoughPotsForgeMod {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NotEnoughPotsCommons.MOD_ID);

  public NotEnoughPotsForgeMod(FMLJavaModLoadingContext context) {
    var eventBus = context.getModEventBus();
    var bridge = new ForgePlatformBridge<>(eventBus, BLOCKS, NotEnoughPotsBlockType.class);
    NotEnoughPotsCommons.init(bridge);
  }
}
