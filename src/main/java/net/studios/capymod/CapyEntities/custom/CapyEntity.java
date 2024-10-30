package net.studios.capymod.CapyEntities.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
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

//IA DE DEL CAPYBARA BASE
public class CapyEntity extends Animal {
    public CapyEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.05f));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.3f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.05D, Ingredient.of(Moditems.SALAD.get()), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        ;
    }

    public static AttributeSupplier.Builder customAttributes() {
        return Animal.createMobAttributes()
        .add(Attributes.MAX_HEALTH, 15D)
                .add(Attributes.FOLLOW_RANGE, 30D)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel capyLevel, AgeableMob ageableMob) {
        return MainEntities.CAPY.get().create(capyLevel);
    }
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Moditems.SALAD.get());
    }
}
