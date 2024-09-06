package com.jothapunkt.spigot.raftcraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUIElement;

import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.ChatColor;


public class MainMenu extends GUI {
    public MainMenu() {
        super(new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        slot(13, new ItemStack(Material.BOOK), new GUIElement((InventoryClickEvent e) -> e.getWhoClicked().sendMessage("Click!"), TriState.TRUE));
    }

    @Override
    protected Inventory setupInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, ChatColor.DARK_GRAY + "Menu");
        return inventory;
    }
}
