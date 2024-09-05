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
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class IceKing extends CustomMob {
    public IceKing() {
        super();
        
        name = "Ice King";
        level = 50;
        type = EntityType.SKELETON;
        stat(Stat.MAX_HEALTH, 4850.0);
        stat(Stat.ARMOR, 100.0);

        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE0ZmE3ZWYzYmMzYzJkMTUzYWUwNGI5NjQwMjE0ZDI5ZTg3MWVmZDhkY2MzYjRiZDg3MDljNTk0MmJhMmNjOSJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.fromRGB(175, 216, 218)));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.fromRGB(119, 187, 217)));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.fromRGB(175, 216, 218)));
    }
}
