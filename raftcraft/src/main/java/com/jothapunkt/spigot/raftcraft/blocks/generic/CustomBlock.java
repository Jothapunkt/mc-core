package com.jothapunkt.spigot.raftcraft.blocks.generic;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

import com.jothapunkt.spigot.raftcraft.util.CustomClass;

public class CustomBlock<T extends PersistentDataHolder> extends CustomClass<T> {
    public void onPlace(BlockPlaceEvent event) {}
}
