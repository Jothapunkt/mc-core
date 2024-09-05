package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.DamagingAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.effects.visual.LightningRune;

import net.md_5.bungee.api.ChatColor;

public class LightningAbility extends RightClickAbility implements DamagingAbility {
    private double range = 4.0;

    public LightningAbility() {
        name = "Smite";
        cooldown = 2.0;
    }

    @Override
    public void use(Player player) {
        Location location = player.getLocation().add(player.getLocation().getDirection().setY(0).normalize().multiply(2));
        location.setPitch(0);
        new LightningRune(location, player);
    }

    @Override
    public double getBaseDamage() {
        return 50.0;
    }

    public double getRange() {
        return range;
    }
}
