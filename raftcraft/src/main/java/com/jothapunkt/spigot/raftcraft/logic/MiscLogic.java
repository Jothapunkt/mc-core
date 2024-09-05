package com.jothapunkt.spigot.raftcraft.logic;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.effects.generic.VisualEffect;


public class MiscLogic {
    public static BukkitRunnable getCleanupLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    // Delete temporary entities like damage number displays if expired
                    for (Entity entity : world.getEntities()) {
                        if (entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "expirationTime"))) {
                            if (entity.getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "expirationTime"), PersistentDataType.LONG) < System.currentTimeMillis()) {
                                entity.remove();
                            }
                        }
                    }

                    // If no players in a raft world, attempt to unload it
                    if (!world.getKey().getNamespace().equals("raft")) {
                        continue;
                    }

                    boolean hasPlayers = false;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getLocation().getWorld() == world) {
                            hasPlayers = true;
                        }
                    }

                    if (!hasPlayers) {
                        Bukkit.getLogger().info("Unloading world " + world.getName());
                        Bukkit.unloadWorld(world, true);
                        world = null;
                        System.gc();
                    }
                }
            }
        };
    }

    public static BukkitRunnable getEffectsLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    // Do animation ticks
                    for (Entity entity : world.getEntities()) {
                        if (entity.getMetadata("visualEffect").size() > 0) {
                            VisualEffect effect = (VisualEffect) entity.getMetadata("visualEffect").get(0).value();
                            effect.tick();
                        }
                    }
                }
            }
        };
    }
}
