package com.jothapunkt.spigot.raftcraft.types;
import net.md_5.bungee.api.ChatColor;

public enum Rarity {
    TRASH(ChatColor.DARK_GRAY),
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.DARK_BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD);

    private ChatColor rarityColor;

    private Rarity(ChatColor rarityColor) {
        this.rarityColor = rarityColor;
    }

    public ChatColor getRarityColor() {
        return rarityColor;
    }
}
