package com.jothapunkt.spigot.raftcraft.mobs.swamp;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;

import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;

public class SwampKnight extends CustomMob {
    public SwampKnight() {
        name = "Swamp Knight";
        type = EntityType.BOGGED;

        equip(EquipmentSlot.HEAD, ItemRegistry.get(Material.CHAINMAIL_HELMET));
        equip(EquipmentSlot.CHEST, ItemRegistry.get(Material.CHAINMAIL_CHESTPLATE));
        equip(EquipmentSlot.OFF_HAND, ItemRegistry.get(Material.SHIELD));
        equip(EquipmentSlot.HAND, ItemRegistry.get(Material.IRON_SWORD));
    }
}
