package com.jothapunkt.spigot.raftcraft.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class LootBundle {
    private List<LootItemInstance> loot = new ArrayList<>();;
    private ChatColor accentColor = ChatColor.DARK_PURPLE;
    private ChatColor mainColor = ChatColor.WHITE;
    private String name = null;
    
    public LootBundle() {

    }

    public LootBundle(String name, ChatColor mainColor) {
        this.name = name;
        this.mainColor = mainColor;
    }

    public LootBundle(String name, ChatColor mainColor, ChatColor accentColor) {
        this.name = name;
        this.mainColor = mainColor;
        this.accentColor = accentColor;
    }

    public void add(LootItemInstance item) {
        loot.add(item);
    }

    public void add(List<LootItemInstance> items) {
        for (LootItemInstance item : items) {
            loot.add(item);
        }
    }

    public void grantTo(Player player) {
        if (name != null) {
            player.sendMessage(accentColor + "--- " + mainColor + name + accentColor + " ---");
        }

        for (LootItemInstance item : loot) {
            item.grantTo(player, true);
        }

        if (name != null) {
            player.sendMessage(accentColor + "-".repeat(7 + name.length()));
        }
    }
}
