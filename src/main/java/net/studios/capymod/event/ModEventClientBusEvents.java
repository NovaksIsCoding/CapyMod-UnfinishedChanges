package net.studios.capymod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.studios.capymod.CapyEntities.client.CapybaraModel;
import net.studios.capymod.CapyEntities.client.ModModelLayers;
import net.studios.capymod.CapyMod;

@Mod.EventBusSubscriber(modid = CapyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.CAPY_LAYER, CapybaraModel::createBodyLayer);
    }

}
