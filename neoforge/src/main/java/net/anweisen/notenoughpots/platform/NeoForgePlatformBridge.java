package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class NeoForgePlatformBridge<T extends Enum<T> & IPottedBlockType> implements IPlatformBridge<T> {

  private final Map<T, DeferredHolder<Block, Block>> pottedBlocks;

  private final IEventBus eventBus;
  private final DeferredRegister<Block> register;

  public NeoForgePlatformBridge(IEventBus eventBus, DeferredRegister<Block> register, Class<T> enumClass) {
    this.eventBus = eventBus;
    this.register = register;
    this.pottedBlocks = new EnumMap<>(enumClass);
  }

  @Override
  public void registerPottedBlock(T type) {
    pottedBlocks.put(type, register.register(type.getName(), type::createPottedFlowerBlock));
  }

  public void finishRegistration() {
    register.register(eventBus);
  }

  @Override
  public Block getPottedBlock(T type) {
    return pottedBlocks.get(type).get();
  }

}
