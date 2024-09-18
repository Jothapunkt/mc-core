package com.jothapunkt.spigot.raftcraft.dungeons.generic;

import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.errors.WorldError;
import com.jothapunkt.spigot.raftcraft.rafts.Raft;
import com.jothapunkt.spigot.raftcraft.util.CustomClassRegistry;
import com.jothapunkt.spigot.raftcraft.util.Worlds;
import com.jothapunkt.spigot.raftcraft.worlds.CustomWorld;

public class DungeonInstance {
    private UUID identifier;
    private Dungeon<?> dungeon;

    public DungeonInstance(Dungeon<?> dungeon) {
        this.identifier = UUID.randomUUID();
        this.dungeon = dungeon;
    }

    public String getWorldName() {
        return "instance_" + identifier.toString();
    }

    public Dungeon<?> getDungeon() {
        return dungeon;
    }

    public void send(Player player) {
        player.sendMessage("Sending you to instance " + identifier.toString() + "...");
        World world = Worlds.getIfLoaded(getWorldName());

        if (world == null) {
            try {
                world = Worlds.clone(dungeon.getWorldName(), getWorldName());
            } catch(WorldError e) {
                e.sendTo(player);
            }
        }

        if (world == null) {
            player.sendMessage("Failed to send you to instance :(");
            return;
        }

        world.setMetadata("dungeon", new FixedMetadataValue(RaftCraft.getInstance(), this));
        Worlds.teleport(player, world);
    }

    public CustomWorld getCustomWorld() {
        World world = Worlds.getIfLoaded(getWorldName());
        return Worlds.getCustomWorld(world);
    }

    public static DungeonInstance getDungeonInstance(World world) { 
        if (world.hasMetadata("dungeon")) {
            return (DungeonInstance) world.getMetadata("dungeon").get(0).value();
        }

        return null;
    }
}
