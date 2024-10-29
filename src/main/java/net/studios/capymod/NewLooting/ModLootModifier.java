package net.studios.capymod.NewLooting;

import com.mojang.serialization.Codec;
import io.netty.channel.ChannelId;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.IEventBusInvokeDispatcher;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.studios.capymod.CapyMod;

public class ModLootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> ADDED_LOOT_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CapyMod.MOD_ID);

   public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
           ADDED_LOOT_SERIALIZERS.register("add_item", AddedItemsModifier.CODEC);

    public static void register(IEventBus eventBus) {
        ADDED_LOOT_SERIALIZERS.register(eventBus);
    }
}