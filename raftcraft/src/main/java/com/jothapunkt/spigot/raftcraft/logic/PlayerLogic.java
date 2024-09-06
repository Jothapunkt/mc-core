package com.jothapunkt.spigot.raftcraft.logic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.Markers;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.util.Time;


public class PlayerLogic {
    public static BukkitRunnable getPlayerUpdateLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerInfo info = new PlayerInfo(player);
                    info.calculateStats();
                    info.applyStats();
                    player.setPlayerTime(Time.now().getTimeOfDay(), true);

                    if (player.getVehicle() instanceof Mob) {
                        Mob mount = (Mob) player.getVehicle();
                        player.sendMessage("Vehicle: " + mount.getType());
                        Location target = player.getLocation();
                        target.add(player.getLocation().getDirection().normalize().multiply(10));

                        Bukkit.getMobGoals().removeAllGoals(mount);
                        mount.getPathfinder().moveTo(target);
                    }
                }
            }
        };
    }
}
