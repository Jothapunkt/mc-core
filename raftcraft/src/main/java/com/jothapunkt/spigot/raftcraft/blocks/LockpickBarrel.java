package com.jothapunkt.spigot.raftcraft.blocks;

import org.bukkit.Bukkit;
import org.bukkit.block.Barrel;
import org.bukkit.event.player.PlayerInteractEvent;

import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;
import com.jothapunkt.spigot.raftcraft.gui.LockpickingGUI;
import com.jothapunkt.spigot.raftcraft.gui.PreservingJarGUI;
import com.jothapunkt.spigot.raftcraft.interfaces.InteractableBlock;
import com.jothapunkt.spigot.raftcraft.interfaces.LockpickingTarget;
import com.jothapunkt.spigot.raftcraft.tables.BarrelLootTable;
import com.jothapunkt.spigot.raftcraft.types.Difficulty;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;
import com.jothapunkt.spigot.raftcraft.util.LootTable;

public class LockpickBarrel extends CustomBlock<Barrel> implements InteractableBlock, LockpickingTarget {    
    @Override
    public void onInteract(PlayerInteractEvent event) {
        Bukkit.broadcastMessage("Attempting to lockpick...");
        new LockpickingGUI(this).show(event.getPlayer());
        event.setCancelled(true);
    }

    @Override
    public LootTable getLootTable() {
        return new BarrelLootTable();
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.APPRENTICE;
    }
}
