package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;
import org.joml.Math;

import java.util.HashMap;
import org.bukkit.Location;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import org.bukkit.event.vehicle.VehicleMoveEvent;

public class BoatListener implements Listener {
    @EventHandler
    public void onBoatMove(VehicleMoveEvent event) {
        if (!(
            event.getVehicle() instanceof Boat
            && event.getVehicle().getPassengers().size() > 0
            && event.getVehicle().getPassengers().get(0) instanceof Player
        )) {
            // Only apply to boats being driven by a player
            return;
        }

        Boat boat = (Boat) event.getVehicle();
        Player player = (Player) boat.getPassengers().get(0);
        HashMap<Stat, Double> stats = new PlayerInfo(player).getStats();

        Vector velocity = player.getVelocity();
        velocity.setY(0);

        if (Math.abs(velocity.length()) > 0.01 && Math.abs(event.getFrom().distance(event.getTo())) > 0.2) { 
            boat.setVelocity(boat.getLocation().getDirection().normalize().multiply(stats.get(Stat.BOAT_SPEED) / 100 * 0.3));
            Location motorLocation = boat.getLocation().clone();
            motorLocation.add(motorLocation.getDirection().normalize().multiply(-1));
            motorLocation.setDirection(new Vector(0, 0, 0));

            player.spawnParticle(Particle.BUBBLE, motorLocation, (int) Math.round(stats.get(Stat.BOAT_SPEED) / 100 * 20), 0, 0, 0);
        }
    }

}
