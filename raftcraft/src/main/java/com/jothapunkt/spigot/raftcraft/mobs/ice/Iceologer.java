package com.jothapunkt.spigot.raftcraft.mobs.ice;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;

import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherBoots;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherChestplate;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherLeggings;
import com.jothapunkt.spigot.raftcraft.items.vanilla.PlayerHead;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class Iceologer extends CustomMob {
    public Iceologer() {
        name = "Iceologer";
        type = EntityType.ZOMBIE;

        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2FkMDgxNDI0ZmRhOWExMjM5ODhjNTE1N2ZkMTIxYmQ2MjZhMmQ5OGU5ZWEyYTI2Y2I5OTcyZDVkZGU0NWFhMyJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.fromRGB(10, 32, 77)));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.fromRGB(10, 32, 77)));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.fromRGB(150, 156, 156)));
    }
}
