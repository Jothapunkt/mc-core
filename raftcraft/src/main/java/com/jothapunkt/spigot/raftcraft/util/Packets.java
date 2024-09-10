package com.jothapunkt.spigot.raftcraft.util;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.RaftCraft;

import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class Packets {
    public static void broadcast(Packet packet) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            connection.send(packet);
        }
    }
}
