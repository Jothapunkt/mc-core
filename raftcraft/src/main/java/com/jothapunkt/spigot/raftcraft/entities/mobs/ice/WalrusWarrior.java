package com.jothapunkt.spigot.raftcraft.entities.mobs.ice;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherBoots;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherChestplate;
import com.jothapunkt.spigot.raftcraft.items.vanilla.LeatherLeggings;
import com.jothapunkt.spigot.raftcraft.items.vanilla.PlayerHead;
import com.jothapunkt.spigot.raftcraft.types.Skills;
import com.jothapunkt.spigot.raftcraft.util.Items;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

public class WalrusWarrior extends CustomMob {
    public WalrusWarrior() {
        name = "Walrus Warrior";
        type = EntityType.ZOMBIE;

        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDNjZGU1ZjBlNzY0ZGY2MDJhMzYxZTQ0MWE4MGQxODVkYjVkODMwYjI3OTM5OWFjZTVmYTgxMWRhNmZmZjJkIn19fQ=="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate());
        equip(EquipmentSlot.LEGS, new LeatherLeggings());
        equip(EquipmentSlot.FEET, new LeatherBoots());
    }
}
