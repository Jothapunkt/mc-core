package com.jothapunkt.spigot.raftcraft.effects.visual;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.effects.generic.LineProjectile;

public class BeeProjectile extends LineProjectile {
    public BeeProjectile(Location location, Player caster) {
        super(location, caster);
        Bee bee = (Bee) location.getWorld().spawnEntity(location, EntityType.BEE);
        bee.setAI(false);

        addPart("bee", bee);
    }
}
