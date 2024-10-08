package com.jothapunkt.spigot.raftcraft.mounts;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Pig;
import org.bukkit.event.player.PlayerInteractEvent;

import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;

public class PigMount extends Mount<Pig> {
    public PigMount() {
        type = EntityType.PIG;
    }

    @Override
    public Pig spawn(Location location) {
        Pig pig = super.spawn(location);
        pig.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(.3);
        return pig;
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        event.getPlayer().getVehicle().setVelocity(
            event.getPlayer().getLocation().getDirection().normalize().multiply(2).setY(1.5)
        );
    }
}
