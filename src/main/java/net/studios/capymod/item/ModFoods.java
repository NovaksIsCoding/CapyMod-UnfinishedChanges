package net.studios.capymod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //Agrego la comida SALAD para regenerarte y al capybara
    public static final FoodProperties SALAD = new FoodProperties.Builder().alwaysEat().fast().nutrition(1).saturationMod(0.6f).build();
}

