package com.jothapunkt.spigot.raftcraft.items.materials;

import java.awt.Color;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;

import net.md_5.bungee.api.ChatColor;

public class Thatch extends CustomItem {
    public Thatch() {
        name = ChatColor.YELLOW + "Thatch";
        baseItem = new ItemStack(Material.WHEAT);
    }
}
