package com.jothapunkt.spigot.raftcraft.items.armor;

import java.awt.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Colors;

public class SpeedBoots extends CustomItem {
    public SpeedBoots() {
        rarity = Rarity.UNCOMMON;
        name = Colors.gradientText("Speed Boots", new Color(255, 255, 255), new Color(130, 130, 130));
        baseItem = new ItemStack(Material.LEATHER_BOOTS);
        stat(Stat.SPEED, 100.0);
        stat(Stat.SWIM_SPEED, 100.0);
    }
}
