package com.jothapunkt.spigot.raftcraft.entities.mobs.generic;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class CustomZombie extends CustomMob {
    protected boolean baby = false;

    public CustomZombie() {
        type = EntityType.ZOMBIE;
    }

    public Zombie spawn(Location location) {
        LivingEntity mob = super.spawn(location);

        Zombie zombie = (Zombie) mob;

        if (baby) {
            zombie.setBaby();
        }

        return zombie;
    }
}
