package com.jothapunkt.spigot.raftcraft.entities.mobs.statues;

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

public class SoldierStatue extends CustomMob {
    public SoldierStatue() {
        type = EntityType.SKELETON;
        name = ChatColor.GRAY + "Soldier Statue";
        level = 25;
        stat(Stat.MAX_HEALTH, 1200.0);
        equip(EquipmentSlot.HAND, ItemRegistry.get(Material.NETHERITE_SWORD));
        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmY4NmNkYTk4YzNjMjU0NDllY2FkZmE1MzYzNzQyYTdkNjg0YTU1NjU4NDg0NGI1NjRmNmQ0NGNiMDlmY2NmNyJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.fromRGB(155, 155, 155)));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.fromRGB(155, 155, 155)));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.fromRGB(155, 155, 155)));
    }
}
