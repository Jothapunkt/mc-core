package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;

import net.md_5.bungee.api.ChatColor;

public class TeleportAbility extends RightClickAbility {
    private double range = 8.0;

    public TeleportAbility() {
        name = "Warp";
    }

    @Override
    public void use(Player player) {
        Vector direction = player.getLocation().getDirection().normalize();

        Location target = player.getLocation().clone();
        Location checkBlockAt = player.getLocation().clone();
        checkBlockAt.add(0, 1, 0);

        for (int i = 0; i < range; i++) {
            target.add(direction);
            checkBlockAt.add(direction);

            if (target.getWorld().getBlockAt(checkBlockAt).getType().isSolid()) {
                target.subtract(direction);
                player.sendMessage(ChatColor.RED + "Something is in the way!");
                break;
            } else {
                player.spawnParticle(Particle.PORTAL, target, 10, .05, .05, .05);
            }
        }

        player.teleport(target);
    }
}
