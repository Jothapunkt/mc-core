package com.jothapunkt.spigot.raftcraft.logic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.destroystokyo.paper.entity.ai.Goal;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.menu.MenuItem;
import com.jothapunkt.spigot.raftcraft.items.menu.MountControlItem;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.util.Time;


public class PlayerLogic {
    public static ItemStack getMenuItem(Player player) {
        PlayerInfo info = new PlayerInfo(player);

        if (info.getMount() != null) {
            return new MountControlItem().print();
        }

        return new MenuItem().print();
    }

    public static BukkitRunnable getPlayerUpdateLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerInfo info = new PlayerInfo(player);
                    info.calculateStats();
                    info.applyStats();
                    player.setPlayerTime(Time.now().getTimeOfDay(), true);
                    player.getInventory().setItem(8, getMenuItem(player));

                    if (player.getVehicle() instanceof Mob mount) {
                        Location target = player.getLocation();
                        target.add(player.getLocation().getDirection().normalize().multiply(10));

                        if (new MobInfo(mount).getMeta().get("mountMovement") != null && new MobInfo(mount).getMeta().get("mountMovement").asBoolean()) {
                            mount.getPathfinder().moveTo(target);
                        } else {
                            mount.getPathfinder().stopPathfinding();
                        }
                    }
                }
            }
        };
    }
}
