package com.jothapunkt.spigot.raftcraft.mounts;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.MobGoals;
import com.jothapunkt.spigot.raftcraft.mounts.generic.Mount;
import com.jothapunkt.spigot.raftcraft.util.Markers;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class PhantomMount extends Mount<Phantom> {
    public PhantomMount() {
        type = EntityType.PHANTOM;
    }

    @Override
    public Phantom spawn(Location location) {
        Phantom phantom = super.spawn(location);
        phantom.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(.3);
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
    public Location getTarget(Mob instance, Player rider) {
        // If the mount does not currently have the flying effect, use normal pathing
        if (!(new MobInfo(instance).getMeta().get("mountFlying") != null && new MobInfo(instance).getMeta().get("mountFlying").asLong() > System.currentTimeMillis())) {
            return super.getTarget(instance, rider);
        }

        Bukkit.broadcastMessage("Phantom Target");

        Location target = rider.getLocation();

        if (rider.getTargetBlock(10) != null) {
            target = rider.getTargetBlock(10).getLocation();
            target.add(0, 1, 0);
        } else {
            target.add(rider.getLocation().getDirection().normalize().multiply(10));
        }

        return target;
    }

    @Override
    public void onUpdate(Mob instance, Player rider) {
        if (new MobInfo(instance).getMeta().get("mountMovement") != null && new MobInfo(instance).getMeta().get("mountMovement").asBoolean()) {
            //instance.getPathfinder().moveTo(getTarget(instance, rider));
            Markers.target(getTarget(instance, rider), 1);
        } else {
            instance.getPathfinder().stopPathfinding();
            Bukkit.broadcastMessage("Stopping movement");
        }
    }
}
