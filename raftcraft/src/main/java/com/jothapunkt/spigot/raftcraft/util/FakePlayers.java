package com.jothapunkt.spigot.raftcraft.util;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;

import com.comphenix.packetwrapper.WrapperPlayServerNamedEntitySpawn;
import com.comphenix.packetwrapper.WrapperPlayServerPlayerInfo;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.accessors.FieldAccessor;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.GameType;


public class FakePlayers {
    public static PlayerProfile makePlayerProfile(UUID uuid) {
        return makePlayerProfile(uuid, "");
    }

    public static PlayerProfile makePlayerProfile(String texture) {
        return makePlayerProfile(UUID.randomUUID(), texture);
    }

    public static PlayerProfile makePlayerProfile(UUID uuid, String texture) {
        PlayerProfile profile = Bukkit.getServer().createProfile(uuid, "");
        profile.setProperty(new ProfileProperty("textures", texture));
        return profile;
    }

    public static GameProfile makeGameProfile(UUID uuid, String name, Skin skin) {
        GameProfile profile = new GameProfile(uuid, name);
        profile.getProperties().clear();
        profile.getProperties().put("textures", new Property("textures", skin.getTexture(), skin.getSignature()));
        return profile;
    }

    public static ServerPlayer spawn(Location location, String name) {
        return spawn(location, name, null);
    }

    public static ServerPlayer spawn(Location location, String name, Skin skin) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel world = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile profile = FakePlayers.makeGameProfile(UUID.randomUUID(), name, skin);
        ServerPlayer npc = new ServerPlayer(server, world, profile, ClientInformation.createDefault());
        npc.setPos(location.getX(), location.getY(), location.getZ());

        // Send player join info packet
        ClientboundPlayerInfoUpdatePacket info = new ClientboundPlayerInfoUpdatePacket(
            EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER),
            new ClientboundPlayerInfoUpdatePacket.Entry(
                npc.getUUID(),
                profile,
                true,
                0,
                GameType.CREATIVE,
                Component.literal(name),
                null
            )
        );

        Packets.broadcast(info);

        ClientboundAddEntityPacket entity = new ClientboundAddEntityPacket(npc, 0, new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        Packets.broadcast(entity);

        return npc;
    }
}
