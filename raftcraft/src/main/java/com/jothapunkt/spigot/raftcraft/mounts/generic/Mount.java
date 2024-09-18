package com.jothapunkt.spigot.raftcraft.mounts.generic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.Markers;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class Mount<T extends Mob> extends CustomMob<T> {
    public void summon(Player rider) {
        Mob mount = instantiate(rider.getLocation());
        Bukkit.getMobGoals().removeAllGoals(mount);
        mount.setPassenger(rider);
    }

    public void onDismount(EntityDismountEvent event) {
        Bukkit.broadcastMessage(event.getDismounted().getType().toString());
        event.getDismounted().remove();
        event.getEntity().getWorld().spawnParticle(
            Particle.SMOKE,
            event.getDismounted().getLocation(),
            15,
            .4,
            .4,
            .4,
            0
        );
    }

    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getPlayer().getVehicle() instanceof Mob mob) {
            MobInfo info = new MobInfo(mob);
            if (info.getMeta().get("mountMovement") != null) {
                info.getMeta().set("mountMovement", !info.getMeta().get("mountMovement").asBoolean());
            } else {
                info.getMeta().set("mountMovement", true);
            }
        }
    }

    public Location getTarget(Mob instance, Player rider) {
        Bukkit.broadcastMessage("Base Target");

        Location target = rider.getLocation();

        if (rider.getTargetBlock(10) != null) {
            target = rider.getTargetBlock(10).getLocation();
            target.add(0, 1, 0);
        } else {
            target.add(rider.getLocation().getDirection().normalize().multiply(10));
            for (int y = target.getBlockY(); y > 0; y--) {
                if (!rider.getWorld().getBlockAt(target.getBlockX(), y, target.getBlockZ()).isEmpty()) {
                    target.setY(y);
                }
            }
        }

        return target;
    }

    public void onUpdate(Mob instance, Player rider) {
        if (new MobInfo(instance).getMeta().get("mountMovement") != null && new MobInfo(instance).getMeta().get("mountMovement").asBoolean()) {
            instance.getPathfinder().moveTo(getTarget(instance, rider));
            Markers.target(getTarget(instance, rider), 1);
        } else {
            instance.getPathfinder().stopPathfinding();
            Bukkit.broadcastMessage("Stopping movement");
        }
    }

    public void onRightClick(PlayerInteractEvent event) {}
}