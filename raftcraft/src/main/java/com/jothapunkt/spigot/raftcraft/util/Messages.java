package com.jothapunkt.spigot.raftcraft.util;

import net.md_5.bungee.api.ChatColor;

public class Messages {
    public static String addLootMessage(String loot, int amount) {
        return ChatColor.BOLD + "" + ChatColor.GREEN + "+ " + loot + ChatColor.RESET + ChatColor.DARK_GRAY + " x" + Integer.toString(amount);
    }
}
