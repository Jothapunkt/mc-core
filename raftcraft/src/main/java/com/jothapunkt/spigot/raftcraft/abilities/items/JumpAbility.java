package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;

import net.md_5.bungee.api.ChatColor;

public class JumpAbility extends RightClickAbility {
    private double force = 3.0;

    public JumpAbility() {
        name = "Leap";
        description.add("Leap forward into the skies.");
        cost = 20.0;
        cooldown = 3.0;
    }

    @Override
    public void use(Player player) {
        Vector velocity = player.getLocation().getDirection().normalize().multiply(force);
        velocity.setY(Math.signum(velocity.getY()) * Math.min(Math.abs(velocity.getY()), force / 2));
        player.setVelocity(velocity);
    }
}
