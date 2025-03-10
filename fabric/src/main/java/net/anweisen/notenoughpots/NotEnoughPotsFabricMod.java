package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.FabricPlatformBridge;
import net.fabricmc.api.ModInitializer;

public class NotEnoughPotsFabricMod implements ModInitializer {

  @Override
  public void onInitialize() {
    var bridge = new FabricPlatformBridge<>(Constants.MOD_ID, NotEnoughPotsBlockType.class);
    CommonClass.init(bridge);
  }
}
