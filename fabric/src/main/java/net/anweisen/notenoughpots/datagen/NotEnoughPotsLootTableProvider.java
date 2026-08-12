package net.anweisen.notenoughpots.datagen;

import net.anweisen.notenoughpots.NotEnoughPotsBlockType;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class NotEnoughPotsLootTableProvider extends FabricBlockLootSubProvider {

  protected NotEnoughPotsLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(dataOutput, registryLookup);
  }

  @Override
  public void generate() {
    for (var type : NotEnoughPotsBlockType.values()) {
      add(type.findBlock(), block -> createPotFlowerItemTable(type.getFlowerBlock().asItem()));
    }
  }

}
