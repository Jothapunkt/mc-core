package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomBlockItem;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;

import net.md_5.bungee.api.chat.TextComponent;


public class BlockListener implements Listener {
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.SPAWNER) {
            event.getPlayer().sendMessage("Placed spawner " + event.getItemInHand().getType());

            if (event.getPlayer().getInventory().getItemInMainHand().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
                CustomMob mob = MobRegistry.get(event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"), PersistentDataType.STRING));
                CreatureSpawner spawner = (CreatureSpawner) event.getBlockPlaced().getState();
                spawner.getPersistentDataContainer().set(
                    new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"),
                    PersistentDataType.STRING,
                    mob.getIdentifier()
                );

                spawner.setSpawnedType(mob.getType());
                
                for (SpawnerEntry entry : spawner.getPotentialSpawns()) {
                    event.getPlayer().sendMessage("Can spawn: " + entry.getSnapshot().getEntityType().name());
                }

                spawner.update();

                event.getPlayer().sendMessage(mob.getIdentifier());
            }
        }
    }

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        if (event.getSpawner().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
            CustomMob mob = MobRegistry.get(event.getSpawner().getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"), PersistentDataType.STRING));
            Bukkit.broadcast(new TextComponent(mob.getIdentifier()));
            mob.spawn(event.getLocation());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCustomBlockPlace(BlockPlaceEvent event) {
        CustomItem heldCustomItem = ItemRegistry.get(event.getItemInHand());

        if (!(heldCustomItem instanceof CustomBlockItem<?>)) { return; }

        CustomBlock<PersistentDataHolder> customBlock = ((CustomBlockItem<PersistentDataHolder>) heldCustomItem).getCustomBlock();

        if (event.getBlockPlaced().getState() instanceof PersistentDataHolder) {
            PersistentDataHolder holder = (PersistentDataHolder) event.getBlockPlaced().getState();
            customBlock.setKey(holder);
            ((BlockState) holder).update();
        }
    }
}