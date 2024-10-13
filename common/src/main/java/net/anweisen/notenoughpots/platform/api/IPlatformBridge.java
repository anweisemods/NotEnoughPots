package net.anweisen.notenoughpots.platform.api;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.minecraft.world.level.block.Block;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface IPlatformBridge {

  void registerPottedBlock(NotEnoughPotsBlockType type);

  void finishRegistration();

  Block getPottedBlock(NotEnoughPotsBlockType type);

}
