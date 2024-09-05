package com.jothapunkt.spigot.raftcraft.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.LeftClickAbility;
import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import org.bukkit.event.entity.EntitySpawnEvent;


public class ItemAbilityListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack heldItem = event.getPlayer().getInventory().getItemInMainHand();
        CustomItem heldCustomItem = ItemRegistry.get(heldItem);

        if (heldCustomItem == null) {
            event.getPlayer().sendMessage("Not holding a custom item");
            return;
        }

        if (List.of(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK).contains(event.getAction())) {
            RightClickAbility ability = heldCustomItem.getAbility(RightClickAbility.class);
            if (ability != null) {
                ability.onRightClick(event);
            }
        }

        if (List.of(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK).contains(event.getAction())) {
            LeftClickAbility ability = heldCustomItem.getAbility(LeftClickAbility.class);
            if (ability != null) {
                ability.onLeftClick(event);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // If the held item has a right click ability, call that with a "Right Click" event instead
        ItemStack heldItem = event.getPlayer().getInventory().getItemInMainHand();
        CustomItem heldCustomItem = ItemRegistry.get(heldItem);

        RightClickAbility ability = heldCustomItem.getAbility(RightClickAbility.class);
        if (ability != null) {
            ability.onRightClick(new PlayerInteractEvent(event.getPlayer(), Action.RIGHT_CLICK_AIR, heldItem, null, null));
            event.setCancelled(true);
        }
    }
}