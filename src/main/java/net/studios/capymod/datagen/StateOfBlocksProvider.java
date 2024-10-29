package net.studios.capymod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.studios.capymod.CapyMod;
import net.studios.capymod.blocks.CapyBlocks;
import net.studios.capymod.blocks.custom.LettuceCropBlock;

import java.util.function.Function;

public class StateOfBlocksProvider extends BlockStateProvider {
    public StateOfBlocksProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CapyMod.MOD_ID, exFileHelper);
}
    @Override
    protected void registerStatesAndModels() {
        mLettuceCrop((CropBlock) CapyBlocks.LETTUCE_CROP.get(), "lettuce_stage", "lettuce_stage");
    }


    public void mLettuceCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> lettuceState(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] lettuceState(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((LettuceCropBlock) block).getAgeProperty()),
                new ResourceLocation(CapyMod.MOD_ID, "block/" + textureName + state.getValue(((LettuceCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}