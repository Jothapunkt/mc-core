package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.craftbukkit.block.CraftCreatureSpawner;
import org.bukkit.entity.Mob;
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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.world.item.Item;


public class BlockListener implements Listener {
    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        if (event.getSpawner().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
            CustomMob mob = MobRegistry.get(event.getSpawner().getPersistentDataContainer().get(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"), PersistentDataType.STRING));
            Bukkit.broadcast(new TextComponent(mob.getKey()));
            mob.instantiate(event.getLocation());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCustomBlockPlace(BlockPlaceEvent event) {
        CustomItem heldCustomItem = ItemRegistry.get(event.getPlayer().getInventory().getItem(event.getHand()));

        if (heldCustomItem == null) {
            return;
        }

        event.getPlayer().sendMessage(
            Component.text("Placing " + heldCustomItem.getName())
            .hoverEvent(event.getItemInHand().asHoverEvent())
        );

        if (!(heldCustomItem instanceof CustomBlockItem<?>)) {
            Bukkit.broadcastMessage("Not a custom block item: " + heldCustomItem.getClass().getSimpleName());
            return;
        }

        CustomBlock<PersistentDataHolder> customBlock = ((CustomBlockItem) heldCustomItem).getCustomBlock();

        if (event.getBlockPlaced().getState() instanceof PersistentDataHolder holder) {
            customBlock.setKey(holder);
            ((BlockState) holder).update();
            Bukkit.broadcastMessage("Wrote persistent data onto " + event.getBlockPlaced().getState().getClass().getSimpleName());
        } else {
            Bukkit.broadcastMessage("Not able to write persistent data onto " + event.getBlockPlaced().getState().getClass().getSimpleName());
        }
    }
}