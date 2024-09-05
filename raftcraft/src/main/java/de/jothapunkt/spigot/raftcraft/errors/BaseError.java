package com.jothapunkt.spigot.raftcraft.errors;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BaseError extends Exception {
    public BaseError(String reason) {
        super(reason);
    }

    public void sendTo(Player player) {
        player.sendMessage(ChatColor.RED + getMessage());
    }
}
