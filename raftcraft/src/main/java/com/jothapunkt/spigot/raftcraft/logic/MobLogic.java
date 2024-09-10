package com.jothapunkt.spigot.raftcraft.logic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.scheduler.BukkitRunnable;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.entities.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;


public class MobLogic {
    public static List<MobInfo> getCustomMobs() {
        List<MobInfo> mobs = new ArrayList<>();

        for (World world : Bukkit.getWorlds()) {
            mobs.addAll(getCustomMobs(world));
        }

        return mobs;
    }

    public static List<MobInfo> getCustomMobs(World world) {
        List<MobInfo> mobs = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Mob mob) {
                if (MobRegistry.get(mob) != null) {
                    mobs.add(new MobInfo(mob));
                }
            }
        }

        return mobs;
    }

    public static BukkitRunnable getMobUpdateLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (MobInfo info : getCustomMobs()) {
                    info.onUpdate();
                }

                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntitiesByClass(Mob.class)) {
                        Mob mob = (Mob) entity;
                        
                    }
                }
            }
        };
    }
}
