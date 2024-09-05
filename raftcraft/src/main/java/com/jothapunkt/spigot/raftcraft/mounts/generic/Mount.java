package com.jothapunkt.spigot.raftcraft.mounts.generic;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;

public class Mount {
    private CustomMob type;

    public Mount(CustomMob type) {
        this.type = type;
    }

    public void summon(Player rider) {
        LivingEntity mount = type.spawn(rider.getLocation());
        mount.setPassenger(rider);
    }
}
