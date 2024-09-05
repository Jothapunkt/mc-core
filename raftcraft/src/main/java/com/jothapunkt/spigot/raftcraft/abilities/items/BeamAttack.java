package com.jothapunkt.spigot.raftcraft.abilities.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.LeftClickAbility;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;
import com.jothapunkt.spigot.raftcraft.events.ItemAbilityHitEvent;
import com.jothapunkt.spigot.raftcraft.types.DamageType;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.DamagingAbility;

import net.md_5.bungee.api.ChatColor;

public class BeamAttack extends LeftClickAbility implements DamagingAbility {
    double range = 15.0;
    Particle particle = Particle.FIREWORK;
    double spread = 0.0;
    int amount = 5;

    public BeamAttack() {
        name = "Beam";
    }

    public BeamAttack(Particle particle) {
        this();
        this.particle = particle;
    }

    @Override
    public void use(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();

        Location checkLocation = player.getLocation().clone();
        checkLocation.add(0, 1, 0);

        Location targetLocation = checkLocation.clone();
        targetLocation.add(direction.clone().multiply(range));
        Mob hitEntity = null;

        for (Entity entity : player.getLocation().getWorld().getEntities()) {
            if (!(entity instanceof Mob)) {
                continue;
            }

            RayTraceResult hit = entity.getBoundingBox().rayTrace(checkLocation.toVector(), direction, range);

            if (hit != null) {
                if (player.hasLineOfSight(entity) && checkLocation.toVector().distance(hit.getHitPosition()) < checkLocation.distance(targetLocation)) {
                    hitEntity = (Mob) entity;
                    targetLocation = new Location(entity.getWorld(), hit.getHitPosition().getX(), hit.getHitPosition().getY(), hit.getHitPosition().getZ());
                }
            }
        }

        if (hitEntity != null) {
            ItemAbilityHitEvent e = new ItemAbilityHitEvent(this, player, hitEntity);
            Bukkit.getServer().getPluginManager().callEvent(e);
            if (!e.isCancelled()) {
                MobInfo info = new MobInfo(hitEntity);
                info.applyDamage(player, e.getAbility().getDamage(player), DamageType.NORMAL);
            }
        }

        double distance = checkLocation.distance(targetLocation);
        direction.normalize().multiply(.5);

        for (double i = 0.0; i < distance; i+=0.5) {
            if (checkLocation.getWorld().getBlockAt(checkLocation).getType().isSolid()) {
                break;
            } else {
                player.spawnParticle(this.particle, checkLocation, amount, spread, spread, spread, 0.0);
            }
            checkLocation.add(direction);
        }
    }

    @Override
    public double getBaseDamage() {
        return 10.0;
    }
}
