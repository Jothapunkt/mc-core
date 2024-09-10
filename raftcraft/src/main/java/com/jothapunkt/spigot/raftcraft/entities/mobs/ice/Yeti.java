package com.jothapunkt.spigot.raftcraft.entities.mobs.ice;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;

import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherBoots;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherChestplate;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherLeggings;
import com.jothapunkt.spigot.raftcraft.items.vanilla.PlayerHead;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class Yeti extends CustomMob {
    public Yeti() {
        name = "Yeti";
        type = EntityType.SKELETON;

        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFjNzNjY2Q3MDlkMGY3Y2JjZTk4YTZhNDhlMTk0ZDlhNmJhMjdmOGVkZjZlMTI5NTRlYmY5M2FmZWUwOWRlNSJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.WHITE));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.WHITE));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.WHITE));
    }
}
