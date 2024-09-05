package com.jothapunkt.spigot.raftcraft.items.materials;

import java.awt.Color;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;

import net.md_5.bungee.api.ChatColor;

public class PalmLeaf extends CustomItem {
    public PalmLeaf() {
        name = ChatColor.of(new Color(0, 170, 0)) + "Palm Leaf";
        baseItem = new ItemStack(Material.KELP);
    }
}
