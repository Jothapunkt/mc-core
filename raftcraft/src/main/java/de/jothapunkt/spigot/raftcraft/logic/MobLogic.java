package com.jothapunkt.spigot.raftcraft.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.building.ModelBuildingEvent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;


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
            if (entity instanceof Mob) {
                if (entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
                    mobs.add(new MobInfo((Mob) entity));
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
