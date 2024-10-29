package net.studios.capymod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.studios.capymod.CapyMod;
import net.studios.capymod.item.Moditems;

public class CapyItemsModeler extends ItemModelProvider {

    public CapyItemsModeler(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CapyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(Moditems.LETTUCE);
        simpleItem(Moditems.LETTUCE_SEEDS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(CapyMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
