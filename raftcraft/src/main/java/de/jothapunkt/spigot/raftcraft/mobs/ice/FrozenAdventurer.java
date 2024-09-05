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

public class FrozenAdventurer extends CustomMob {
    public FrozenAdventurer() {
        name = "Frozen Adventurer";
        type = EntityType.SKELETON;

        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I1NTJjMTUwMDA1MDQ5YWM3YWIwY2VkY2IzMmIxMTRjYzQwZTM1NGIyMDUyZDE4NTJhNTYyOWVjYjllNGMwMyJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.fromRGB(205, 236, 247)));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.fromRGB(205, 236, 247)));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.fromRGB(205, 236, 247)));
    }
}
