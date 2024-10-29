package net.studios.capymod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.studios.capymod.CapyMod;
import net.studios.capymod.blocks.CapyBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CapyMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CAPY_TAB = CREATIVE_MODE_TABS.register("capymod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.LETTUCE.get()))
                    .title(Component.translatable("creativetab.capymod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Moditems.LETTUCE.get());
                        pOutput.accept(Moditems.SALAD.get());
                        pOutput.accept(Moditems.LETTUCE_SEEDS.get());
                        pOutput.accept(Moditems.CAPYBARA_MEAT.get());
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
