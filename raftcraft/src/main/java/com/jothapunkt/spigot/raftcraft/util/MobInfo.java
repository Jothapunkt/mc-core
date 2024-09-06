package com.jothapunkt.spigot.raftcraft.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.modifiers.effects.CustomEffect;
import com.jothapunkt.spigot.raftcraft.types.DamageType;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class MobInfo {
    private CustomMob mob;
    private Mob instance;
    private Meta m;

    public MobInfo(Mob entity) {
        this.mob = MobRegistry.get(entity);
        this.m = new Meta(entity);
        this.instance = entity;
    }

    public void setStat(Stat stat, Double value) {
        m.setStat(stat, value);
    }

    public Double getStat(Stat stat) {
        return m.getStat(stat);
    }

    public void onUpdate() {
        updateEffects();
        calculateStats();
        applyStats();
    }

    public void applyStats() {
        instance.setCustomName(mob.getInstanceName(instance));
        instance.setCustomNameVisible(true);
    }

    public void applyDamage(double damage, DamageType type) {
        applyDamage(null, damage, type);
    }

    public void applyKnockback(Location source, double strength) {
        applyKnockback(source, strength, false);
    } 

    public void applyKnockback(Location source, double strength, boolean faceSource) {
        Vector direction = instance.getLocation().subtract(source).toVector().normalize().multiply(strength);
        direction.setY(Math.max(strength / 2, direction.getY()));
        instance.setVelocity(direction);
    }

    public void applyDamage(Player source, double damage, DamageType type) {
        Location damageNumberLocation = instance.getLocation().add(0, 1, 0);

        if (source != null) {
            m.set("LastDamagedBy", source.getUniqueId());
            instance.playHurtAnimation(source.getLocation().getYaw());
            // Move damage number closer to the attacking player
            damageNumberLocation.add(source.getLocation().subtract(damageNumberLocation).toVector().normalize());
        } else {
            instance.playHurtAnimation(0);
        }

        double postMitigationDamage = damage / Math.max(getStat(Stat.ARMOR) / 100, 1.0);
        
        Displays.damageDisplay(damageNumberLocation, postMitigationDamage, type);
        double health = getStat(Stat.HEALTH) - postMitigationDamage;

        if (health <= 0) {
            instance.setHealth(0.0);
        } else {
            instance.setHealth(instance.getMaxHealth());
            setStat(Stat.HEALTH, health);
            Bukkit.broadcastMessage("Remaining HP: " + health);
        }
    }

    public void calculateStats() {
        HashMap<Stat, Double> stats = new HashMap<>();

        // Start with mob stats
        for (Entry<Stat, Double> stat: mob.getStats().entrySet()) {
            stats.put(stat.getKey(), stat.getValue());
        }
        
        for (Entry<Stat, Double> stat: stats.entrySet()) {
            if (stat.getKey().getStatic()) { // Only static stats are recalculated, health, mana etc. is not
                m.setStat(stat.getKey(), stat.getValue());
            }
        }
    }

    public Meta getMeta() {
        return m;
    }

    public CustomMob getMob() {
        return mob;
    }

    public Mob getInstance() {
        return instance;
    }

    public void applyEffect(CustomEffect effect) {
        getEffects().add(effect);
        effect.onApply(instance);
    }

    public void updateEffects() {   
        List<CustomEffect> effects = getEffects();

        for (CustomEffect effect : effects) {
            if (System.currentTimeMillis() > effect.getEndTime()) {
                effect.onRemove(instance);
                effects.remove(effect);
            } else {
                effect.onTick(instance);
            }
        }

    }

    public List<CustomEffect> getEffects() {
        MetadataValue effectsValue = m.get("effects");

        if (effectsValue == null) {
            effectsValue = m.set("effects", new ArrayList<CustomEffect>());
        }
        
        List<CustomEffect> effects = (List<CustomEffect>) (effectsValue.value());

        return effects;
    }

    public boolean hasEffect(Class<?> effectClass) {
        for (CustomEffect effect : getEffects()) {
            if (effectClass.isInstance(effect)) {
                return true;
            }
        }

        return false;
    }
}
