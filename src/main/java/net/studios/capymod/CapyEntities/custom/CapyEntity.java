package net.studios.capymod.CapyEntities.custom;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.studios.capymod.CapyEntities.MainEntities;
import net.studios.capymod.item.ModFoods;
import net.studios.capymod.item.Moditems;
import org.jetbrains.annotations.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.SynchedEntityData;

//IA DEL CAPYBARA BASE
public class CapyEntity extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> DATA_SITTING = SynchedEntityData.defineId(CapyEntity.class, EntityDataSerializers.BOOLEAN);
    //Se establece como TamableAnimal para poderlo hacerlo un mob tameable
    public CapyEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide){
            setupAnimationStates();
        }

        if(this.isSitting()) {
            this.navigation.stop();
            this.setPose(Pose.SITTING);
        } else{
            this.setPose(Pose.STANDING);
        }

    }
    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6.0f, 1.0f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 12.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.4f));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0f));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.2D, Ingredient.of(Moditems.SALAD_FOOD.get()), false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        ;
    }

    //Velocidad
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
        .add(Attributes.MAX_HEALTH, 15D)
                .add(Attributes.FOLLOW_RANGE, 30D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel capyLevel, AgeableMob ageableMob) {
        return MainEntities.CAPY.get().create(capyLevel);
    }
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Moditems.SALAD_FOOD.get());
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound(){
        return SoundEvents.CAT_AMBIENT;
    }

    @Nullable
    @Override
    public SoundEvent getHurtSound(DamageSource pDamageSource){
        return super.getHurtSound(pDamageSource);
    }

    @Nullable
    @Override
    public SoundEvent getDeathSound(){
        return super.getDeathSound();
    }

    @Override
    protected void defineSynchedData(){
        super.defineSynchedData();
        this.entityData.define(DATA_SITTING, false);
    }

    public boolean isSitting() {
        return this.entityData.get(DATA_SITTING);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(DATA_SITTING, sitting);
    }
    //Animacion de cuando se sienta y para

    //Tamear al caybara
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        // Verificar si el capybara ya fue tameado
        if (this.isTame() && this.isOwnedBy(player)) {
                if(player.isShiftKeyDown()) {
                    //Acciones que realizará el capybara
                    this.setSitting(!this.isSitting());

                    if(this.isSitting()) {
                        this.setOrderedToSit(true);
                        player.displayClientMessage(Component.literal("Capybara is now sitting"), true);
                    } else {
                        this.setOrderedToSit(false);
                        player.displayClientMessage(Component.literal("Capybara is now following"), true);
                    }
                    return InteractionResult.SUCCESS;
                }
        } else {
            // Si el mob no esta tameado, se necesitarán los items necesarios
            if (itemstack.is(Moditems.LETTUCE.get())) { // Item para tamearlo
                if (!this.level().isClientSide) {
                    if (this.random.nextInt(5) == 0) { // chance de tamear
                        this.tame(player);
                        this.level().broadcastEntityEvent(this, (byte)7); // Particulas de corazones
                    } else {
                        this.level().broadcastEntityEvent(this, (byte)6); // Si no se logra tamear, saldrán particulas de humo.
                    }
                }
                // Consume one item from the player's hand
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return super.mobInteract(player, hand);
    }
}
