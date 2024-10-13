package net.anweisen.notenoughpots.platform;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.anweisen.notenoughpots.NotEnoughPotsForgeMod;
import net.anweisen.notenoughpots.platform.api.IPlatformBridge;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ForgePlatformBridge implements IPlatformBridge {

  private final Map<NotEnoughPotsBlockType, RegistryObject<Block>> pottedBlocks = new EnumMap<>(NotEnoughPotsBlockType.class);

  private final IEventBus eventBus;

  public ForgePlatformBridge(IEventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void registerPottedBlock(NotEnoughPotsBlockType type) {
    pottedBlocks.put(type, NotEnoughPotsForgeMod.BLOCKS.register(type.getName(), () -> NotEnoughPotsBlockType.flowerPot(type.getFlowerBlock())));
  }

  public void finishRegistration() {
    NotEnoughPotsForgeMod.BLOCKS.register(eventBus);
  }

  @Override
  public Block getPottedBlock(NotEnoughPotsBlockType type) {
    return pottedBlocks.get(type).get();
  }

}
