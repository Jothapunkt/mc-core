package com.jothapunkt.spigot.raftcraft.dungeons.generic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.jothapunkt.spigot.raftcraft.util.Strings;
import com.jothapunkt.spigot.raftcraft.worlds.CustomWorld;

import net.md_5.bungee.api.ChatColor;

public class Dungeon<T extends CustomWorld> {
    protected String worldName = "";
    protected Material icon = Material.COBBLESTONE;
    protected List<String> description = new ArrayList<>();
    
    public Dungeon() {
        
    }

    public String getLongName() {
        return Strings.snakeToCamelCase(worldName);
    }

    public ItemStack getEntranceItem() {
        ItemStack infoItem = new ItemStack(icon);

        ItemMeta meta = infoItem.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.GOLD + "Enter " + getLongName());
        infoItem.setItemMeta(meta);

        return infoItem;
    }

    public String getWorldName() {
        return worldName;
    }

    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
    
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    public DungeonInstance instantiate() {
        return new DungeonInstance(this);
    }
}
