package com.jothapunkt.spigot.raftcraft.mounts;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;

public class PigMount extends Mount {
    public PigMount() {
        type = EntityType.PIG;
    }

    @Override
    public Mob spawn(Location location) {
        Mob pig = super.spawn(location);
        pig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(.3);
        return pig;
    }
}
