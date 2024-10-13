package net.anweisen.notenoughpots;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public enum NotEnoughPotsBlockType {

  POTTED_SUGAR_CANE(Blocks.SUGAR_CANE),
  POTTED_SUNFLOWER(Blocks.SUNFLOWER),
  POTTED_ROSE_BUSH(Blocks.ROSE_BUSH),
  POTTED_PEONY(Blocks.PEONY),
  POTTED_LILAC(Blocks.LILAC),
  POTTED_LARGE_FERN(Blocks.LARGE_FERN),
  POTTED_TWISTING_VINES(Blocks.TWISTING_VINES),
  POTTED_SWEET_BERRY_BUSH(Blocks.SWEET_BERRY_BUSH),
  POTTED_MELON_STEM(Blocks.MELON_STEM),
  POTTED_PUMPKIN_STEM(Blocks.PUMPKIN_STEM),
  ;

  private final String name;
  private final Block flower;

  NotEnoughPotsBlockType(Block flower) {
    this.flower = flower;
    this.name = this.name().toLowerCase();
  }

  public static Block flowerPot(Block flower) {
    return new FlowerPotBlock(flower, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
  }

  public static Block registerBuiltIn(String name, Block block) {
    return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
  }

  public Block getFlowerBlock() {
    return this.flower;
  }

  public String getName() {
    return this.name;
  }

  public Block findBlock() {
    return CommonClass.getBridge().getPottedBlock(this);
  }

  public ResourceLocation toResourceLocation() {
    return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, this.name);
  }

}
