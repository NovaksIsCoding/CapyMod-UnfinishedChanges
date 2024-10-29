package net.studios.capymod.CapyEntities.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.studios.capymod.CapyEntities.custom.CapyEntity;
import net.studios.capymod.CapyMod;

public class CapyRenderer extends MobRenderer<CapyEntity, CapybaraModel<CapyEntity>> {
    public CapyRenderer(EntityRendererProvider.Context context) {
        super(context, new CapybaraModel<>(context.bakeLayer(ModModelLayers.CAPY_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(CapyEntity capyEntity) {
        return new ResourceLocation(CapyMod.MOD_ID, "textures/entity/capy.png");
    }

    @Override
    public void render(CapyEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.8f, 0.8f, 0.8f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
