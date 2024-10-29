package net.studios.capymod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //Agrego la comida SALAD para regenerarte y al capybara
    public static final FoodProperties SALAD = new FoodProperties.Builder().alwaysEat().fast().nutrition(1).saturationMod(0.6f).build();
    public static final FoodProperties CAPYBARA_MEAT = new FoodProperties.Builder().alwaysEat().nutrition(6).saturationMod(3.5f).build();
}

