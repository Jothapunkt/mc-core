package com.jothapunkt.spigot.raftcraft.recipes.crafting;

import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipeRegistry;
import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.items.wands.IceStaff;

public class CraftingRecipes {
    public static void register() {
        CustomRecipeRegistry.getInstance().register(
            new CustomRecipe(new IceStaff().print())
                .ingredient(1, new ItemStack(Material.ICE, 1))
                .ingredient(4, new ItemStack(Material.STICK))
                .ingredient(7, new ItemStack(Material.STICK))
        );
    }
}
