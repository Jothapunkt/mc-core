package com.jothapunkt.spigot.raftcraft.items.armor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class SailorsHat extends CustomItem {
    public SailorsHat() {
        rarity = Rarity.UNCOMMON;
        name = "Sailors Hat";
        baseItem = new ItemStack(Material.LEATHER_HELMET);
        stat(Stat.BOAT_SPEED, 100.0);
    }
}
