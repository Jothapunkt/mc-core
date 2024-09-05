package com.jothapunkt.spigot.raftcraft.blocks;

import org.bukkit.Bukkit;
import org.bukkit.block.Barrel;
import org.bukkit.event.player.PlayerInteractEvent;

import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;
import com.jothapunkt.spigot.raftcraft.gui.PreservingJarGUI;
import com.jothapunkt.spigot.raftcraft.interfaces.InteractableBlock;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class PreservingJarBlock extends CustomBlock<Barrel> implements InteractableBlock {
    static {CustomClassRegistry.getInstance().register(new PreservingJarBlock());}
    
    @Override
    public void onInteract(PlayerInteractEvent event) {
        Bukkit.broadcastMessage("Opening Preserving Jar");
        new PreservingJarGUI((Barrel) event.getClickedBlock().getState()).show(event.getPlayer());
        event.setCancelled(true);
    }
}
