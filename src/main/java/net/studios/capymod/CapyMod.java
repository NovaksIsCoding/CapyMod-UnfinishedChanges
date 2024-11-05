package net.studios.capymod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.studios.capymod.CapyEntities.MainEntities;
import net.studios.capymod.CapyEntities.client.CapyRenderer;
import net.studios.capymod.NewLooting.ModLootModifier;
import net.studios.capymod.blocks.CapyBlocks;
import net.studios.capymod.item.ModCreativeModeTabs;
import net.studios.capymod.item.Moditems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(CapyMod.MOD_ID)
public class CapyMod {
    public static final String MOD_ID = "capymod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CapyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        //Mobs de capybaras
        MainEntities.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        //Modificaciones al loot para las semillas
        ModLootModifier.register(modEventBus);
        //Registro del item creado para el tameo del capybara
        Moditems.register(modEventBus);
        //Bloques
        CapyBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::setupSpawnPlacements);
    }

    private void setupSpawnPlacements(SpawnPlacementRegisterEvent event) {
        MainEntities.setupSpawns(event);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    // Tambien aqui se hace el registro dentro del menu de creativo
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(Moditems.LETTUCE);
            event.accept(Moditems.LETTUCE_SEEDS);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(Moditems.SALAD_FOOD);
            event.accept(Moditems.CAPYBARA_MEAT);
        }
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(Moditems.CAPY_SPAWN_EGG);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(MainEntities.CAPY.get(), CapyRenderer::new);
        }
    }
}
