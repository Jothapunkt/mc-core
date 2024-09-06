package com.jothapunkt.spigot.raftcraft.recipes;

public enum DefaultRecipeTypes implements RecipeType {
    CRAFTING;

    public String getRecipeType() {
        return name();
    }
}
