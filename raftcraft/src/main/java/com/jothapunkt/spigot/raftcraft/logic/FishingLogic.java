package com.jothapunkt.spigot.raftcraft.logic;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.effects.generic.VisualEffect;
import com.jothapunkt.spigot.raftcraft.events.FishHookHitWaterEvent;
import com.jothapunkt.spigot.raftcraft.items.ItemRegistry;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.generic.FishingRod;


public class FishingLogic {
    public static BukkitRunnable getFishingEventLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    // Do animation ticks
                    for (Entity entity : world.getEntities()) {
                        if (entity instanceof FishHook) {
                            FishHook hook = (FishHook) entity;
                            Player caster = (Player) hook.getShooter();

                            ItemStack heldItem = caster.getInventory().getItemInMainHand();
                            CustomItem heldCustomItem = ItemRegistry.get(heldItem);
                            FishingRod customRod = heldCustomItem instanceof FishingRod ? (FishingRod) heldCustomItem : null;

                            if (hook.isInWaterOrBubbleColumn()) {
                                if (hook.getMetadata("inWater").size() == 0) {
                                    Bukkit.getPluginManager().callEvent(new FishHookHitWaterEvent(hook));
                                    hook.setMetadata("inWater", new FixedMetadataValue(RaftCraft.getInstance(), true));
                                    if (customRod != null) {
                                        customRod.onWaterHit(hook);
                                    }
                                }
                            }

                            if (customRod != null) {
                                customRod.onUpdate(hook);
                            }
                        }
                    }
                }
            }
        };
    }
}
