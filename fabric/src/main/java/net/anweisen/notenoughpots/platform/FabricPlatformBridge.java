package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.IPottedBlockType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class FabricPlatformBridge<T extends Enum<T> & IPottedBlockType> implements IPlatformBridge<T> {

  private final Map<T, Block> pottedBlocks;
  private final String modId;

  public FabricPlatformBridge(String modId, Class<T> enumClass) {
    this.pottedBlocks = new EnumMap<>(enumClass);
    this.modId = modId;
  }

  @Override
  public void registerPottedBlock(T type) {
    pottedBlocks.put(type, registerBuiltIn(type));
  }

  public Block registerBuiltIn(T type) {
    return Registry.register(Registry.BLOCK, type.createResourceLocation(modId), type.createPottedFlowerBlock(modId));
  }

  @Override
  public void finishRegistration() {
    // fabric doesn't need to do anything else
  }

  @Override
  public Block getPottedBlock(T type) {
    return pottedBlocks.get(type);
  }

}
