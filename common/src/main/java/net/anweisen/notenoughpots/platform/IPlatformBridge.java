package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.block.Block;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public interface IPlatformBridge<T extends IPottedBlockType> {

  void registerPottedBlock(T type);

  void finishRegistration();

  Block getPottedBlock(T type);

}
