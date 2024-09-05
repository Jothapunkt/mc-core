package com.jothapunkt.spigot.raftcraft.tables;
import com.jothapunkt.spigot.raftcraft.util.LootTable;
import com.jothapunkt.spigot.raftcraft.util.WeightedOption;

import net.md_5.bungee.api.ChatColor;

import com.jothapunkt.spigot.raftcraft.util.LootItem;
import java.util.List;

import java.awt.Color;
import org.bukkit.Material;


public class BarrelLootTable extends LootTable {
    public BarrelLootTable() {
        this.name = "Barrel";
        this.mainColor = ChatColor.of(new Color(128, 43, 0));

        this.options = List.of(
            new WeightedOption<LootItem>(new LootItem(Material.COBBLESTONE, 2, 5), 1000),
            new WeightedOption<LootItem>(new LootItem(Material.RAW_IRON, 1, 1), 1000),
            new WeightedOption<LootItem>(new LootItem(Material.POTATO, 1, 1), 1000),
            new WeightedOption<LootItem>(new LootItem(Material.CARROT, 1, 1), 1000)
        );
    }
}
