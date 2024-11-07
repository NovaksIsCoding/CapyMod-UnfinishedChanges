package net.studios.capymod.CapyEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.studios.capymod.CapyEntities.custom.CapyEntity;
import net.studios.capymod.CapyMod;
import net.minecraft.world.entity.Mob;



public class MainEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CapyMod.MOD_ID);

    public static final RegistryObject<EntityType<CapyEntity>> CAPY =
            ENTITY_TYPES.register("capy", () -> EntityType.Builder.of(CapyEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f).build("capy"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}