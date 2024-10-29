package net.studios.capymod.event;

import net.minecraft.data.Main;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.studios.capymod.CapyEntities.MainEntities;
import net.studios.capymod.CapyEntities.custom.CapyEntity;
import net.studios.capymod.CapyMod;

@Mod.EventBusSubscriber(modid = CapyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MainEntities.CAPY.get(), CapyEntity.createMobAttributes().build());
    }
}
