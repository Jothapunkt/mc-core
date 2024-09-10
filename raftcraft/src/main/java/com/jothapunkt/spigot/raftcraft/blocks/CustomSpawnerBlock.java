package com.jothapunkt.spigot.raftcraft.blocks;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.entity.Mob;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.blocks.generic.CustomBlock;
import com.jothapunkt.spigot.raftcraft.entities.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.entities.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.items.blocks.CustomSpawner;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;

public class CustomSpawnerBlock extends CustomBlock<CreatureSpawner> {
    static {CustomClassRegistry.getInstance().register(new CustomSpawnerBlock());}
    
    @Override
    public void onPlace(BlockPlaceEvent event) {
        Bukkit.broadcastMessage("Configuring spawner");
        if (CustomClassRegistry.getInstance().get(event.getPlayer().getInventory().getItem(event.getHand())) instanceof CustomSpawner) {
            if (event.getPlayer().getInventory().getItemInMainHand().getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"))) {
                String mobIdentifier = event.getItemInHand()
                                            .getItemMeta()
                                            .getPersistentDataContainer()
                                            .get(
                                                new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"),
                                                PersistentDataType.STRING
                                            );

                CustomMob mob = MobRegistry.get(mobIdentifier);
                
                if (mob == null) {
                    Bukkit.broadcastMessage("Could not find " + mobIdentifier);
                    return;
                }

                CreatureSpawner spawner = (CreatureSpawner) event.getBlockPlaced().getState();
                
                spawner.getPersistentDataContainer().set(
                    new NamespacedKey(RaftCraft.getInstance(), "mob_identifier"),
                    PersistentDataType.STRING,
                    mobIdentifier
                );

                Mob instance = mob.spawn(event.getPlayer().getLocation().add(0, 3, 0));
                spawner.setSpawnedEntity(instance.createSnapshot());
                instance.remove();
                
                for (SpawnerEntry entry : spawner.getPotentialSpawns()) {
                    event.getPlayer().sendMessage("Can spawn: " + entry.getSnapshot().getEntityType().name());
                }

                spawner.update();

                event.getPlayer().sendMessage(mob.getKey());
            }
        }
    }
}
