package com.jothapunkt.spigot.raftcraft.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


public class FishHookHitWaterEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private FishHook hook;

    public FishHookHitWaterEvent(FishHook hook) {
        this.hook = hook;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public FishHook getHook() {
        return hook;
    }
}
