package com.jothapunkt.spigot.raftcraft.mounts.generic;

import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleExitEvent;

import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;

public class Mount extends CustomMob {
    public void summon(Player rider) {
        Mob mount = instantiate(rider.getLocation());
        mount.setPassenger(rider);
    }

    public void onDismount(VehicleExitEvent event) {
        event.getVehicle().remove();
        event.getExited().getWorld().spawnParticle(
            Particle.POOF,
            event.getExited().getLocation(),
            15,
            .4,
            .4,
            .4,
            0
        );
    }
}