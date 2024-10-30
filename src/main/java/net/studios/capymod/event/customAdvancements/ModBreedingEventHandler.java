package net.studios.capymod.event.customAdvancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.studios.capymod.CapyEntities.custom.CapyEntity;

@Mod.EventBusSubscriber
public class ModBreedingEventHandler {
    @SubscribeEvent
    public static void onBabyEntitySpawn(BabyEntitySpawnEvent event) {
        if (event.getParentA() instanceof CapyEntity && event.getParentB() instanceof CapyEntity) {
            if (event.getCausedByPlayer() instanceof ServerPlayer player) {
                MinecraftServer server = player.getServer();
                if (server != null) {
                    Advancement advancement = server.getAdvancements().getAdvancement(new ResourceLocation("capymod:breeding_a_capy"));
                    if (advancement != null) {
                        AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                        if (!progress.isDone()) {
                            player.getAdvancements().award(advancement, "breedingcapy");
                        }
                    }
                }
            }
        }
    }
}
