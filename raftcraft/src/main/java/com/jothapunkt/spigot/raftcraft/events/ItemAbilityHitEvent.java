package com.jothapunkt.spigot.raftcraft.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.DamagingAbility;

public class ItemAbilityHitEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Player caster;
    private Entity target;
    private DamagingAbility ability;
    private double damage;

    public ItemAbilityHitEvent(DamagingAbility ability, Player caster, Entity target) {
        this.caster = caster;
        this.ability = ability;
        this.target = target;
        this.damage = ability.getDamage(caster);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean arg0) {
        cancelled = arg0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public Player getCaster() {
        return caster;
    }

    public DamagingAbility getAbility() {
        return ability;
    }

    public Entity getTarget() {
        return target;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
