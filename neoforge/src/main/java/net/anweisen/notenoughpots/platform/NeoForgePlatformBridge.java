package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsNeoForgeMod;
import net.anweisen.notenoughpots.platform.api.IPlatformBridge;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class NeoForgePlatformBridge implements IPlatformBridge {

  private final Map<NotEnoughPotsBlockType, DeferredHolder<Block, Block>> pottedBlocks = new EnumMap<>(NotEnoughPotsBlockType.class);

  private final IEventBus eventBus;

  public NeoForgePlatformBridge(IEventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void registerPottedBlock(NotEnoughPotsBlockType type) {
    pottedBlocks.put(type, NotEnoughPotsNeoForgeMod.BLOCKS.register(type.getName(), () -> NotEnoughPotsBlockType.flowerPot(type.getFlowerBlock())));
  }

  public void finishRegistration() {
    NotEnoughPotsNeoForgeMod.BLOCKS.register(eventBus);
  }

  @Override
  public Block getPottedBlock(NotEnoughPotsBlockType type) {
    return pottedBlocks.get(type).get();
  }

}
