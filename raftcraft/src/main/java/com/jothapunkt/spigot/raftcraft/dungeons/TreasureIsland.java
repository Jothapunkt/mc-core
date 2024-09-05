package com.jothapunkt.spigot.raftcraft.dungeons;

import org.bukkit.Material;
import java.util.List;
import org.bukkit.event.block.BlockBreakEvent;

import com.jothapunkt.spigot.raftcraft.dungeons.generic.Dungeon;

public class TreasureIsland extends Dungeon {
    public TreasureIsland() {
        worldName = "treasure_island";
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        // Can only break sand, jungle logs and jungle leaves
        if (!List.of(Material.SAND, Material.JUNGLE_LEAVES, Material.JUNGLE_LOG).contains(event.getBlock().getType())) {
            event.setCancelled(true);
            return;
        }

        // Randomly spawn treasure chests when digging sand
        if (event.getBlock().getType() == Material.SAND) {

        }
    }
}
