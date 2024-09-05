package com.jothapunkt.spigot.raftcraft.abilities.items;

import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.DamagingAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.effects.visual.IcicleProjectile;


public class IcicleAbility extends RightClickAbility implements DamagingAbility {
    private double range = 4.0;
    private double freezeDuration = 2.5;

    public IcicleAbility() {
        name = "Icicle";
        cooldown = 0.3;
    }

    @Override
    public void use(Player player) {
        new IcicleProjectile(player.getLocation(), player);
    }

    @Override
    public double getBaseDamage() {
        return 50.0;
    }

    public double getRange() {
        return range;
    }

    public double getFreezeDuration() {
        return freezeDuration;
    }
}
