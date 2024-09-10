package com.jothapunkt.spigot.raftcraft.entities.mobs.misc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;

import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomZombie;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class Dummy extends CustomZombie {
    public Dummy() {
        name = "Dummy";

        equip(EquipmentSlot.HEAD, ItemRegistry.get(Material.LEATHER_HELMET));
        stat(Stat.HEALTH, 9999999.0);
    }

    @Override
    public Zombie spawn(Location location) {
        Zombie mob = super.spawn(location);
        mob.setAI(false);
        return mob;
    }
}
