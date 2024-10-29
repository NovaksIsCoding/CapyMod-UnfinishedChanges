package net.studios.capymod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.studios.capymod.CapyMod;
import net.studios.capymod.NewLooting.AddedItemsModifier;
import net.studios.capymod.item.Moditems;

public class ModdedGlobalLootProvider extends GlobalLootModifierProvider {
    public ModdedGlobalLootProvider(PackOutput output) {
        super(output, CapyMod.MOD_ID);
    }

    @Override
    protected  void start() {
        add("lettuce_seeds_from_village_plains", new AddedItemsModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/village/village_plains")).build(),
            }, Moditems.LETTUCE_SEEDS.get()));
        }
    }