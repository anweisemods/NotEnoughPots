package net.anweisen.notenoughpots;

import net.anweisen.notenoughpots.platform.api.IPlatformBridge;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class CommonClass {

  private static IPlatformBridge bridge;

  public static void init(IPlatformBridge bridge) {
    CommonClass.bridge = bridge;

    for (NotEnoughPotsBlockType block : NotEnoughPotsBlockType.values()) {
      bridge.registerPottedBlock(block);
    }

    bridge.finishRegistration();
  }

  public static IPlatformBridge getBridge() {
    return bridge;
  }
}
