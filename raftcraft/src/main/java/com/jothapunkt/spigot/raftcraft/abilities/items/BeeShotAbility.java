package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.DamagingAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.effects.visual.BeeProjectile;

public class BeeShotAbility extends RightClickAbility implements DamagingAbility {
    private double range = 4.0;

    public BeeShotAbility() {
        name = "Bee Shot";
        cooldown = 1.0;
    }

    @Override
    public void use(Player player) {
        new BeeProjectile(player.getLocation(), player);
    }

    @Override
    public double getBaseDamage() {
        return 50.0;
    }

    public double getRange() {
        return range;
    }
}
