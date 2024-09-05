package com.jothapunkt.spigot.raftcraft.items.swords;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class Claymore extends CustomItem {
    public Claymore() {
        name = "Claymore";
        baseItem = new ItemStack(Material.NETHERITE_SWORD);
        rarity = Rarity.LEGENDARY;
        damage = 500;
        stat(Stat.CRIT_CHANCE, 50.0);
        stat(Stat.CRIT_DAMAGE, 300.0);
        stat(Stat.ATTACK, 200.0);
    }
}
