package com.jothapunkt.spigot.raftcraft.items.vanilla;

import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class IronSword extends VanillaItem {
    public IronSword() {
        super(Material.IRON_SWORD);
        rarity = Rarity.UNCOMMON;
        stat(Stat.ATTACK, 15.0);
        damage = 30;
    }
}
