package com.jothapunkt.spigot.raftcraft.util;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;


public class LootTable extends WeightedTable<LootItem> {
    protected String name = "";
    protected ChatColor mainColor = ChatColor.DARK_PURPLE;
    protected ChatColor accentColor = ChatColor.WHITE;

    public LootBundle roll(Player player, int rolls) {
        LootBundle bundle = new LootBundle(
            name,
            mainColor,
            accentColor
        );

        for (int i = 0; i < rolls; i++) {
            bundle.add(this.choose().roll(player));
        }

        return bundle;
    }
}