package net.studios.capymod.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.studios.capymod.CapyMod;
import net.studios.capymod.blocks.CapyBlocks;
import net.studios.capymod.blocks.custom.LettuceCropBlock;
import net.studios.capymod.item.Moditems;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class CapyLootingItems extends BlockLootSubProvider {
    public CapyLootingItems() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(CapyBlocks.LETTUCE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, 5));

        this.add(CapyBlocks.LETTUCE_CROP.get(), createCropDrops(CapyBlocks.LETTUCE_CROP.get(), Moditems.LETTUCE.get(),
                Moditems.LETTUCE_SEEDS.get(), lootitemcondition$builder));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return CapyBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}