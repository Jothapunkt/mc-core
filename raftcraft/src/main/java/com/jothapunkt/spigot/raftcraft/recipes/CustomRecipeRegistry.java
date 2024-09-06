package com.jothapunkt.spigot.raftcraft.recipes;

import java.util.List;
import java.util.ArrayList;

import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipe;

public class CustomRecipeRegistry {
    protected static CustomRecipeRegistry instance;
    protected List<CustomRecipe> recipes = new ArrayList<>();

    public static CustomRecipeRegistry getInstance() {
        if (instance == null) {
            instance = new CustomRecipeRegistry();
        }

        return instance;
    }

    public void register(CustomRecipe recipe) {
        recipes.add(recipe);
    }

    public List<CustomRecipe> getRecipes() {
        return recipes;
    }
}
