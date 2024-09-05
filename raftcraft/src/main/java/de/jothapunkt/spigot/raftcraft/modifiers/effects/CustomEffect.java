package com.jothapunkt.spigot.raftcraft.modifiers.effects;

import java.util.HashMap;

import org.bukkit.entity.Entity;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.CustomClass;

import net.md_5.bungee.api.ChatColor;

public class CustomEffect extends CustomClass {
    protected long endTime;
    protected String icon = "";
    protected ChatColor color = ChatColor.WHITE;
    protected HashMap<Stat, Double> stats = new HashMap<>();

    public CustomEffect(double duration) {
        endTime = System.currentTimeMillis() + (int) (duration * 1000);
    }

    public HashMap<Stat, Double> getStats() {
        return stats;
    }

    public long getEndTime() {
        return endTime;
    }

    // Event methods to override
    public void onApply(Entity target) {}
    public void onTick(Entity target) {}
    public void onRemove(Entity target) {}
}