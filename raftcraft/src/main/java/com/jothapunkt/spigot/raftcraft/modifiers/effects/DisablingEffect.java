package com.jothapunkt.spigot.raftcraft.modifiers.effects;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;


public class DisablingEffect extends CustomEffect {
    public DisablingEffect(double duration) {
        super(duration);
    }

    @Override
    public void onApply(Entity target) {
        if (target instanceof Mob) {
            ((Mob) target).setAI(false);
        }
    }

    @Override
    public void onRemove(Entity target) {
        if (target instanceof Mob) {
            ((Mob) target).setAI(true);
        }
    }
}