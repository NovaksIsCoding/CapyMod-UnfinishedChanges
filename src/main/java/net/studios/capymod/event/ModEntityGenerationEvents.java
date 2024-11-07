package net.studios.capymod.event;

import net.minecraft.data.Main;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.studios.capymod.CapyEntities.MainEntities;
import net.studios.capymod.CapyEntities.custom.CapyEntity;
import net.studios.capymod.CapyMod;

@Mod.EventBusSubscriber(modid = CapyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityGenerationEvents {
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(MainEntities.CAPY.get(), CapyEntity.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(
                MainEntities.CAPY.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                CapyEntity::canSpawn,
                SpawnPlacementRegisterEvent.Operation.OR
        );
    }
}
