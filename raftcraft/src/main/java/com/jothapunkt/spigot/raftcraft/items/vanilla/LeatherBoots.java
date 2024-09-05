package com.jothapunkt.spigot.raftcraft.items.vanilla;

import org.bukkit.Color;
import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class LeatherBoots extends VanillaItem {
    public LeatherBoots() {
        super(Material.LEATHER_BOOTS);
        rarity = Rarity.UNCOMMON;
        stat(Stat.ARMOR, 5.0);
        stat(Stat.SPEED, 5.0);
    }

    public LeatherBoots(Color color) {
        this();
        baseItem = Items.dye(baseItem, color);
    }
}
