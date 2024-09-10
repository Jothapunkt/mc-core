package com.jothapunkt.spigot.raftcraft.entities.mobs.bamboo;

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
import com.jothapunkt.spigot.raftcraft.types.Stat;

import net.md_5.bungee.api.ChatColor;

public class Pandassassin extends CustomMob {
    public Pandassassin() {
        type = EntityType.SKELETON;
        name = ChatColor.WHITE + "Pandassassin";
        level = 40;
        stat(Stat.MAX_HEALTH, 12000.0);
        equip(EquipmentSlot.HAND, ItemRegistry.get(Material.NETHERITE_SWORD));
        equip(EquipmentSlot.OFF_HAND, ItemRegistry.get(Material.IRON_SWORD));
        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2RhNzJlMGE4YzQwY2IzZDdlODMyMjM2NTk0YzU3NTNjNjA5MGE3MmVhOGFmODlkNWVmMDQ5ZTQ4MWYwYjBmMiJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.BLACK));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.WHITE));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.BLACK));
    }
}
