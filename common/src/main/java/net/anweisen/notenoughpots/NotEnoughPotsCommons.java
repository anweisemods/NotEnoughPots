package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.IPlatformBridge;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class NotEnoughPotsCommons {

  public static final String MOD_ID = "notenoughpots";
  public static final String MOD_NAME = "NotEnoughPots";

  /** ARGB since the 26.1 tint rework, not RGB: without the 0xFF the water came out at alpha 0, i.e. invisible */
  public static final int WARM_WATER_COLOR = 0xFF45ADF2;

  private static IPlatformBridge<NotEnoughPotsBlockType> bridge;

  public static void init(IPlatformBridge<NotEnoughPotsBlockType> bridge) {
    NotEnoughPotsCommons.bridge = bridge;

    for (NotEnoughPotsBlockType block : NotEnoughPotsBlockType.values()) {
      bridge.registerPottedBlock(block);
    }

    bridge.finishRegistration();
  }

  public static IPlatformBridge<NotEnoughPotsBlockType> getBridge() {
    return bridge;
  }
}
