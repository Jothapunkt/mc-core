package com.jothapunkt.spigot.raftcraft.mobs.swamp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;

import com.jothapunkt.spigot.raftcraft.effects.visual.CustomMobNameTag;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomZombie;

public class Wisp extends CustomZombie {
    public Wisp() {
        name = "Wisp";

        equip(EquipmentSlot.HEAD, ItemRegistry.get(Material.JACK_O_LANTERN));
        equip(EquipmentSlot.CHEST, ItemRegistry.get(Material.GOLDEN_CHESTPLATE));
        equip(EquipmentSlot.HAND, ItemRegistry.get(Material.GOLDEN_HOE));

        baby = true;
        invisible = true;
    }

    @Override
    public Zombie spawn(Location location) {
        Zombie wisp = super.spawn(location);

        new CustomMobNameTag(wisp);

        return wisp;
    }
}
