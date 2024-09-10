package com.jothapunkt.spigot.raftcraft.entities.mobs.fishing;

import org.bukkit.Color;
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
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

public class InverseMermaid extends CustomMob {
    public InverseMermaid() {
        type = EntityType.DROWNED;
        level = 10;
        name = ChatColor.AQUA + "Inverse Mermaid";
        
        stat(Stat.MAX_HEALTH, 150.0);

        equip(EquipmentSlot.HEAD, new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNiYjRkYmQ2MmViNWVmMTdhMWQxMmVkOTY0ZDc4MjlkOWQwYTNkNzg0ZWFkN2E4ZDU5YzExOWM0MzI4YTFmZCJ9fX0="));
        equip(EquipmentSlot.CHEST, new LeatherChestplate(Color.fromRGB(70, 205, 222)));
        equip(EquipmentSlot.LEGS, new LeatherLeggings(Color.fromRGB(70, 205, 222)));
        equip(EquipmentSlot.FEET, new LeatherBoots(Color.fromRGB(70, 205, 222)));
    }

    @Override
    public void onKill(Mob instance, Player killer) {
        new PlayerInfo(killer).grantSkillXP(Skills.FISHING, 100);
    }
}
