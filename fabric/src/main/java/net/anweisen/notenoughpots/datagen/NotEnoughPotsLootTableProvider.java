package net.anweisen.notenoughpots.datagen;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class NotEnoughPotsLootTableProvider extends FabricBlockLootTableProvider {

  protected NotEnoughPotsLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(dataOutput, registryLookup);
  }

  @Override
  public void generate() {
    for (var type : NotEnoughPotsBlockType.values()) {
      add(type.findBlock(), block -> createPotFlowerItemTable(type.getFlowerBlock().asItem()));
    }
  }

}
