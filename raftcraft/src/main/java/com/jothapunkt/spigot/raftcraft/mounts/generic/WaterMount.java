package com.jothapunkt.spigot.raftcraft.mounts.generic;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.Markers;
import com.jothapunkt.spigot.raftcraft.util.Maths;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;
import com.jothapunkt.spigot.raftcraft.util.Strings;

import net.md_5.bungee.api.ChatColor;

public class WaterMount<T extends Mob> extends FreeMovementMount<T> {
    @Override
    public void onUpdate(Mob instance, Player rider) {
        // Dismount if not in water and above a solid block
        if (!instance.isInWaterOrBubbleColumn() && instance.getLocation().add(0, -1, 0).getBlock().getType().isSolid()) {
            instance.eject();
            return;
        }

        float pitch = Maths.clamp(rider.getLocation().getPitch(), -30f, 30f);
        instance.setRotation(rider.getLocation().getYaw(), pitch);

        if (instance.isInWaterOrBubbleColumn() && new MobInfo(instance).getMeta().get("mountMovement") != null && new MobInfo(instance).getMeta().get("mountMovement").asBoolean()) {
            instance.setVelocity(rider.getLocation().getDirection().normalize().multiply(speed));
        }
    }
}