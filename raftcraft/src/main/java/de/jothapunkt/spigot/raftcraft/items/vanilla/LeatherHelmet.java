package com.jothapunkt.spigot.raftcraft.items.vanilla;

import org.bukkit.Color;
import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class LeatherHelmet extends VanillaItem {
    public LeatherHelmet() {
        super(Material.LEATHER_HELMET);
        rarity = Rarity.UNCOMMON;
        stat(Stat.ARMOR, 5.0);
        stat(Stat.SPEED, 5.0);
    }

    public LeatherHelmet(Color color) {
        this();
        baseItem = Items.dye(baseItem, color);
    }
}
