package com.jothapunkt.spigot.raftcraft.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;


public class Items {
    public static ItemStack head(String texture) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        PlayerProfile profile = FakePlayers.makePlayerProfile(texture);
        meta.setPlayerProfile(profile);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack dye(ItemStack item, Color color) {
        if (!(item.getItemMeta() instanceof LeatherArmorMeta)) {
            return item;
        }

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack dyed(Material material, Color color) {
        return dye(new ItemStack(material), color);
    }

    public static void drop(Location location, ItemStack itemStack) {
        Item item = (Item) location.getWorld().spawnEntity(location, EntityType.ITEM);
        item.setCanMobPickup(false);
        item.setItemStack(itemStack);
    }
}
