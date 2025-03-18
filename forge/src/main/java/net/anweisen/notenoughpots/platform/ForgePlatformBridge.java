package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ForgePlatformBridge<T extends Enum<T> & IPottedBlockType> implements IPlatformBridge<T> {

  private final Map<T, RegistryObject<Block>> pottedBlocks;

  private final IEventBus eventBus;
  private final DeferredRegister<Block> register;

  public ForgePlatformBridge(IEventBus eventBus, DeferredRegister<Block> register, Class<T> enumClass) {
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
