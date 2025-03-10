package net.anweisen.notenoughpots.platform.api;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.world.level.block.Block;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface IPlatformBridge<T extends IPottedBlockType> {

  void registerPottedBlock(T type);

  void finishRegistration();

  Block getPottedBlock(T type);

}
