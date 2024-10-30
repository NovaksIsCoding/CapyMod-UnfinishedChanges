package net.studios.capymod.event.customAdvancements;

import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

@Mod.EventBusSubscriber
public class JoinEventHandler {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            MinecraftServer server = player.getServer();
            if (server != null) {
                Advancement advancement = server.getAdvancements().getAdvancement(new ResourceLocation("capymod:join_advancement"));
                if (advancement != null) {
                    AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                    if (!progress.isDone()) { // Only grants the advancement if not already completed
                        player.getAdvancements().award(advancement, "joined_game");
                    }
                }
            }
        }
    }
}
