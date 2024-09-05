package com.jothapunkt.spigot.raftcraft.effects.interactables;

import java.lang.invoke.MethodHandles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataHolder;

import com.jothapunkt.spigot.raftcraft.dungeons.generic.Dungeons;
import com.jothapunkt.spigot.raftcraft.effects.visual.TreasureIslandEffect;
import com.jothapunkt.spigot.raftcraft.gui.DungeonEntranceGUI;
import com.jothapunkt.spigot.raftcraft.interfaces.InteractableEntity;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class DungeonTeleporter extends CustomInteractable implements InteractableEntity {
    static {
        CustomClassRegistry.getInstance().register(new DungeonTeleporter(Dungeons.TREASURE_ISLAND));
    }
    
    protected Dungeons dungeon;

    public DungeonTeleporter(Dungeons dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    protected Interaction instantiateRaw(Location location) {
        Interaction box = (Interaction) location.getWorld().spawnEntity(location, EntityType.INTERACTION);

        box.setInteractionHeight(3f);
        box.setInteractionWidth(3f);
        
        new TreasureIslandEffect(box);

        return box;
    }

    @Override
    public void onInteract(PlayerInteractEntityEvent event) {
        event.getPlayer().sendMessage("Interact Entity!");
        new DungeonEntranceGUI(dungeon.getDungeon()).show(event.getPlayer());
    }
}
