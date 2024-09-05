package com.jothapunkt.spigot.raftcraft.modifiers.effects;

import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

import net.md_5.bungee.api.ChatColor;

public class Frozen extends DisablingEffect {
    static {CustomClassRegistry.getInstance().register(new Frozen(0));}

    public Frozen(double duration) {
        super(duration);
        icon = "";
        color = ChatColor.AQUA;
    }
}