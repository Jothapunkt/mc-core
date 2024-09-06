package com.jothapunkt.spigot.raftcraft.recipes;

import java.util.List;
import java.util.HashMap;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.recipes.RecipeType;
import com.jothapunkt.spigot.raftcraft.recipes.DefaultRecipeTypes;

public class CustomRecipe {
    protected HashMap<Integer, ItemStack> ingredients = new HashMap<>();
    protected ItemStack result;
    protected RecipeType type;

    public CustomRecipe(ItemStack result) {
        this(result, DefaultRecipeTypes.CRAFTING);
    }
    
    public CustomRecipe(ItemStack result, RecipeType type) {
        this.result = result;
        this.type = type;
    }

    public CustomRecipe ingredient(Integer slot, ItemStack ingredient) {
        ingredients.put(slot, ingredient);
        return this;
    }

    public HashMap<Integer, ItemStack> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }
}  
