package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ForgePlatformBridge<T extends Enum<T> & IPottedBlockType> implements IPlatformBridge<T> {

  private final Map<T, Block> pottedBlocks;

  private final String modId;

  public ForgePlatformBridge(String modId, Class<T> enumClass) {
    this.modId = modId;
    this.pottedBlocks = new EnumMap<>(enumClass);
  }

  @Override
  public void registerPottedBlock(T type) {
    // 1.13 port: no DeferredRegister/RegistryObject indirection -- the block is created eagerly and
    // only handed to the registry once RegistryEvent.Register<Block> fires
    Block block = type.createPottedFlowerBlock(modId);
    block.setRegistryName(type.createResourceLocation(modId));
    pottedBlocks.put(type, block);
  }

  @Override
  public void finishRegistration() {
    // the blocks are passed on by NotEnoughPotsForgeMod when the registry event fires
  }

  public void registerAll(IForgeRegistry<Block> registry) {
    pottedBlocks.values().forEach(registry::register);
  }

  @Override
  public Block getPottedBlock(T type) {
    return pottedBlocks.get(type);
  }

}
