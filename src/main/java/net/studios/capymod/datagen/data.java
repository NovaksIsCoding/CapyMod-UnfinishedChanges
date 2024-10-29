package net.studios.capymod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.studios.capymod.CapyMod;
import net.studios.capymod.datagen.loot.CapyLootingItems;
import org.lwjgl.system.macosx.CGEventTapCallBack;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CapyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class data {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new StateOfBlocksProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(), new CapyItemsModeler(packOutput, existingFileHelper));

        generator.addProvider(event.includeClient(), new ModdedGlobalLootProvider(packOutput));

    }
}