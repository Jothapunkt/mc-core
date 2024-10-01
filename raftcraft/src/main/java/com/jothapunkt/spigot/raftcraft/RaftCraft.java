package com.jothapunkt.spigot.raftcraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jothapunkt.spigot.raftcraft.commands.CommandDungeons;
import com.jothapunkt.spigot.raftcraft.commands.CommandFlotsam;
import com.jothapunkt.spigot.raftcraft.commands.CommandInspect;
import com.jothapunkt.spigot.raftcraft.commands.CommandRaft;
import com.jothapunkt.spigot.raftcraft.commands.CommandSkills;
import com.jothapunkt.spigot.raftcraft.commands.CommandSpawn;
import com.jothapunkt.spigot.raftcraft.commands.CommandTinker;
import com.jothapunkt.spigot.raftcraft.commands.CommandWorlds;
import com.jothapunkt.spigot.raftcraft.commands.CommandMounts;
import com.jothapunkt.spigot.raftcraft.commands.CommandRecipes;
import com.jothapunkt.spigot.raftcraft.listeners.BlockListener;
import com.jothapunkt.spigot.raftcraft.listeners.BoatListener;
import com.jothapunkt.spigot.raftcraft.listeners.CollectionListener;
import com.jothapunkt.spigot.raftcraft.listeners.CombatListener;
import com.jothapunkt.spigot.raftcraft.listeners.CustomClassListener;
import com.jothapunkt.spigot.raftcraft.listeners.ItemAbilityListener;
import com.jothapunkt.spigot.raftcraft.listeners.MobListener;
import com.jothapunkt.spigot.raftcraft.listeners.WorldListener;
import com.jothapunkt.spigot.raftcraft.listeners.FishingListener;
import com.jothapunkt.spigot.raftcraft.listeners.GUIListener;
import com.jothapunkt.spigot.raftcraft.listeners.PlayerListener;
import com.jothapunkt.spigot.raftcraft.logic.FishingLogic;
import com.jothapunkt.spigot.raftcraft.logic.FlotsamLogic;
import com.jothapunkt.spigot.raftcraft.logic.MiscLogic;
import com.jothapunkt.spigot.raftcraft.logic.MobLogic;
import com.jothapunkt.spigot.raftcraft.logic.PlayerLogic;
import com.jothapunkt.spigot.raftcraft.types.Wind;
import com.jothapunkt.spigot.raftcraft.util.Initializers;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;
import com.jothapunkt.spigot.raftcraft.recipes.crafting.CraftingRecipes;
import com.jothapunkt.spigot.raftcraft.setup.RegisterItems;
import com.jothapunkt.spigot.raftcraft.setup.RegisterMobs;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.ProtocolLibrary;


public class RaftCraft extends JavaPlugin {
    private static RaftCraft instance;
    private Wind wind = new Wind();
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        Initializers.scoreboard();
        // Initialize scores and stats
        for (Player player : Bukkit.getOnlinePlayers()) {
            new PlayerInfo(player).initializeStats();
        }

        // Event Handlers
        getServer().getPluginManager().registerEvents(new CollectionListener(), this);
        getServer().getPluginManager().registerEvents(new ItemAbilityListener(), this);
        getServer().getPluginManager().registerEvents(new BoatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
        getServer().getPluginManager().registerEvents(new MobListener(), this);
        getServer().getPluginManager().registerEvents(new FishingListener(), this);
        getServer().getPluginManager().registerEvents(new CustomClassListener(), this);
        getServer().getPluginManager().registerEvents(new GUIListener(), this);

        // Register Commands
        this.getCommand("flotsam").setExecutor(new CommandFlotsam());
        this.getCommand("raft").setExecutor(new CommandRaft());
        this.getCommand("worlds").setExecutor(new CommandWorlds());
        this.getCommand("dungeons").setExecutor(new CommandDungeons());
        this.getCommand("mounts").setExecutor(new CommandMounts());
        this.getCommand("skills").setExecutor(new CommandSkills());
        this.getCommand("spawn").setExecutor(new CommandSpawn());
        this.getCommand("recipes").setExecutor(new CommandRecipes());
        this.getCommand("inspect").setExecutor(new CommandInspect());
        this.getCommand("tinker").setExecutor(new CommandTinker());

        // Start Logic Loops
        FlotsamLogic.getFlotsamCollisionsLoop().runTaskTimer(this,  0, 2); // Every 0.1s
        PlayerLogic.getPlayerUpdateLoop().runTaskTimer(this, 0, 10); // Every 0.5s
        MiscLogic.getCleanupLoop().runTaskTimer(this, 0, 10);
        MobLogic.getMobUpdateLoop().runTaskTimer(this, 0, 10);
        MiscLogic.getEffectsLoop().runTaskTimer(this, 0, 1);
        FishingLogic.getFishingEventLoop().runTaskTimer(this, 0, 3);

        // Register custom classes, do general setup
        CraftingRecipes.register();
        RegisterMobs.register();
        RegisterItems.register();
    }

    @Override
    public void onDisable() {
    }

    public Wind getWind() {
        return wind;
    }

    public static RaftCraft getInstance() {
        return instance;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
