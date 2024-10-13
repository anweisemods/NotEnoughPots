package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.platform.api.IPlatformBridge;
import net.minecraft.world.level.block.Block;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class FabricPlatformBridge implements IPlatformBridge {

  private final Map<NotEnoughPotsBlockType, Block> pottedBlocks = new EnumMap<>(NotEnoughPotsBlockType.class);

  @Override
  public void registerPottedBlock(NotEnoughPotsBlockType type) {
    pottedBlocks.put(type, NotEnoughPotsBlockType.registerBuiltIn(type.getName(), NotEnoughPotsBlockType.flowerPot(type.getFlowerBlock())));
  }

  @Override
  public void finishRegistration() {
    // fabric doesn't need to do anything else
  }

  @Override
  public Block getPottedBlock(NotEnoughPotsBlockType type) {
    return pottedBlocks.get(type);
  }

}
