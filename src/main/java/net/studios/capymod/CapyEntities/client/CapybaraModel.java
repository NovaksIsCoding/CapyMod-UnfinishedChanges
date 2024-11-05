package net.studios.capymod.CapyEntities.client;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.studios.capymod.CapyEntities.animations.ModAnimDef;
import net.studios.capymod.CapyEntities.custom.CapyEntity;

public class CapybaraModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "capy"), "main");
	private final ModelPart Capybara;
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Legs;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart HindLegs;
	private final ModelPart Left;
	private final ModelPart Right;

	public CapybaraModel(ModelPart root) {
		this.Capybara = root.getChild("Capybara");
		this.Body = this.Capybara.getChild("Body");
		this.Head = this.Capybara.getChild("Head");
		this.Legs = this.Capybara.getChild("Legs");
		this.LeftLeg = this.Legs.getChild("LeftLeg");
		this.RightLeg = this.Legs.getChild("RightLeg");
		this.HindLegs = this.Legs.getChild("HindLegs");
		this.Left = this.HindLegs.getChild("Left");
		this.Right = this.HindLegs.getChild("Right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Capybara = partdefinition.addOrReplaceChild("Capybara", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Body = Capybara.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -8.0F, -2.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = Capybara.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 14).addBox(0.5F, -7.0F, -8.0F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(6, 26).mirror().addBox(4.35F, -8.0F, -3.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(6, 26).addBox(0.65F, -8.0F, -3.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(7, 6).addBox(1.0F, -6.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition Legs = Capybara.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition LeftLeg = Legs.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(5, 23).mirror().addBox(2.2F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(14, 8).addBox(1.7F, -3.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = Legs.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(14, 8).mirror().addBox(-1.7F, -3.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(5, 23).addBox(-1.2F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HindLegs = Legs.addOrReplaceChild("HindLegs", CubeListBuilder.create(), PartPose.offset(4.0F, -2.0F, 4.0F));

		PartDefinition Left = HindLegs.addOrReplaceChild("Left", CubeListBuilder.create().texOffs(10, 19).mirror().addBox(-1.3F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(16, 21).addBox(-1.8F, -2.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Right = HindLegs.addOrReplaceChild("Right", CubeListBuilder.create().texOffs(10, 19).addBox(-5.7F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 21).mirror().addBox(-6.2F, -2.0F, -0.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimDef.WALK, limbSwing, limbSwingAmount, 4f, 3f);
	}
	private void applyHeadRotation(float pNetHeadyaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadyaw = Mth.clamp(pNetHeadyaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.Head.yRot = pNetHeadyaw * ((float) Math.PI / 180F);
		this.Head.xRot = pHeadPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Capybara.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Capybara;
	}
}