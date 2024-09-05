package com.jothapunkt.spigot.raftcraft.items.materials;

import java.awt.Color;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;

import net.md_5.bungee.api.ChatColor;

public class Plank extends CustomItem {
    public Plank() {
        name = ChatColor.of(new Color(128, 43, 0)) + "Plank";
        baseItem = new ItemStack(Material.SPRUCE_PLANKS);
    }
}
