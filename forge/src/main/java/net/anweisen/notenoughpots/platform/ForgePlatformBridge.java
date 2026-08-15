package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
// 1.16 port: RegistryObject lives in net.minecraftforge.fml here
// (it moved to fmllegacy in 1.17, then to net.minecraftforge.registries in 1.18+)
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ForgePlatformBridge<T extends Enum<T> & IPottedBlockType> implements IPlatformBridge<T> {

  private final Map<T, RegistryObject<Block>> pottedBlocks;

  private final String modId;
  private final IEventBus eventBus;
  private final DeferredRegister<Block> register;

  public ForgePlatformBridge(String modId, IEventBus eventBus, DeferredRegister<Block> register, Class<T> enumClass) {
    this.modId = modId;
    this.eventBus = eventBus;
    this.register = register;
    this.pottedBlocks = new EnumMap<>(enumClass);
  }

  @Override
  public void registerPottedBlock(T type) {
    pottedBlocks.put(type, register.register(type.getName(), () -> type.createPottedFlowerBlock(modId)));
  }

  public void finishRegistration() {
    register.register(eventBus);
  }

  @Override
  public Block getPottedBlock(T type) {
    return pottedBlocks.get(type).get();
  }

}
