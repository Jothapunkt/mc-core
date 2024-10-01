package com.jothapunkt.spigot.raftcraft.mounts;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.MobGoals;
import com.jothapunkt.spigot.raftcraft.mounts.generic.FreeMovementMount;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;
import com.jothapunkt.spigot.raftcraft.util.Markers;
import com.jothapunkt.spigot.raftcraft.util.Maths;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class PhantomMount extends FreeMovementMount<Phantom> {
    protected double speed = 0.5;

    public PhantomMount() {
        name = "Phantom";
        type = EntityType.PHANTOM;
    }

    @Override
    public Phantom spawn(Location location) {
        Phantom phantom = super.spawn(location);
        phantom.setSize(5);
        return phantom;
    }

    @Override
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getPlayer().getVehicle() instanceof Mob mob) {
            new MobInfo(mob).getMeta().set("mountFlying", System.currentTimeMillis() + 10000);
            event.getPlayer().sendMessage("up up and away!");
        }
    }

    @Override
    public void onUpdate(Mob instance, Player rider) {
        if (instance.isInWaterOrBubbleColumn()) {
            instance.eject();
            return;
        }

        super.onUpdate(instance, rider);
    }
}
