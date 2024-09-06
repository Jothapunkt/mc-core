package com.jothapunkt.spigot.raftcraft.mounts.generic;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class Mount extends CustomMob {
    public void summon(Player rider) {
        Mob mount = instantiate(rider.getLocation());
        Bukkit.getMobGoals().removeAllGoals(mount);
        mount.setPassenger(rider);
    }

    public void onDismount(VehicleExitEvent event) {
        event.getVehicle().remove();
        event.getExited().getWorld().spawnParticle(
            Particle.SMOKE,
            event.getExited().getLocation(),
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

    public void onRightClick(PlayerInteractEvent event) {}
    public void onDrop() {}
}