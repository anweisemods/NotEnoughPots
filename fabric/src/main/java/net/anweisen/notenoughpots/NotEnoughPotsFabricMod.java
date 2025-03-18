package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.FabricPlatformBridge;
import net.fabricmc.api.ModInitializer;

public class NotEnoughPotsFabricMod implements ModInitializer {

  @Override
  public void onInitialize() {
    var bridge = new FabricPlatformBridge<>(NotEnoughPotsCommons.MOD_ID, NotEnoughPotsBlockType.class);
    NotEnoughPotsCommons.init(bridge);
  }
}
