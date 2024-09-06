package com.jothapunkt.spigot.raftcraft.gui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipeRegistry;
import com.jothapunkt.spigot.raftcraft.recipes.CustomRecipe;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;

import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.ChatColor;

public class RecipeListGUI extends GUI {
    protected int page = 0;

    public RecipeListGUI() {
        super(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
    }

    @Override
    protected void updateInventory() {
        super.updateInventory();
        List<CustomRecipe> recipes = CustomRecipeRegistry.getInstance().getRecipes();

        for (int i = 0; i < 27; i++) {
            // Skip first and last slot in each row
            //if (i % 9 == 0 || i % 8 == 0) { continue; }
            // Return when all recipes are displayed
            if (i >= recipes.size()) { break; }
            CustomRecipe recipe = recipes.get(i);
            slot(i, recipe.getResult(), new GUIElement());
        }

        slot(35, new ItemStack(Material.ARROW), new GUIElement((InventoryClickEvent e) -> e.getWhoClicked().sendMessage("Next Page!")));
    }
    
    @Override
    protected Inventory setupInventory() {
        return Bukkit.createInventory(this, 36, ChatColor.DARK_GRAY + "Recipe Search");
    }
}
