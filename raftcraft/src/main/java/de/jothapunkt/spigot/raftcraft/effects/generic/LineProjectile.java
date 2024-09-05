package com.jothapunkt.spigot.raftcraft.effects.generic;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.logic.MobLogic;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class LineProjectile extends VisualEffect {
    protected Player caster;
    protected double speed = 2.0;

    public LineProjectile(Location location, Player caster) {
        super(location);
        duration = 40;
        this.caster = caster;
    }

    @Override
    public void tick() {
        super.tick();
        move(location.getDirection().normalize().multiply(speed));
        for (MobInfo info : MobLogic.getCustomMobs(location.getWorld())) {
            for (Entity part : parts.values()) {
                if (info.getInstance().getBoundingBox().overlaps(part.getBoundingBox())) {
                    onHit(info);
                    return;
                }
            }
        }
    }

    public void onHit(MobInfo target) {
        onExpire();
    }
}
