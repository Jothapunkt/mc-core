package com.jothapunkt.spigot.raftcraft.items.vanilla;

import org.bukkit.Color;
import org.bukkit.Material;

import com.jothapunkt.spigot.raftcraft.items.generic.VanillaItem;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class PlayerHead extends VanillaItem {
    public PlayerHead() {
        super(Material.PLAYER_HEAD);
    }

    public PlayerHead(String texture) {
        this();
        baseItem = Items.head(texture);
    }
}
