package com.jothapunkt.spigot.raftcraft.tables;
import com.jothapunkt.spigot.raftcraft.util.LootTable;
import com.jothapunkt.spigot.raftcraft.util.WeightedOption;

import net.md_5.bungee.api.ChatColor;

import com.jothapunkt.spigot.raftcraft.util.LootItem;

import java.awt.Color;
import java.util.List;

import org.bukkit.Material;

public class PotLootTable extends LootTable {
    public PotLootTable() {
        this.name = "Pot";
        this.mainColor = ChatColor.of(new Color(180, 43, 0));
        this.options = List.of(
            new WeightedOption<LootItem>(new LootItem(Material.BRICK, 1, 1), 1000),
            new WeightedOption<LootItem>(new LootItem(Material.STRING, 1, 1), 1000),
            new WeightedOption<LootItem>(new LootItem(Material.LEATHER, 1, 1), 1000)
        );
    }
}
