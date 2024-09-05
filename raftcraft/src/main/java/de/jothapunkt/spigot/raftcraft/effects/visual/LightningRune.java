package com.jothapunkt.spigot.raftcraft.effects.visual;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import com.jothapunkt.spigot.raftcraft.abilities.items.LightningAbility;
import com.jothapunkt.spigot.raftcraft.effects.generic.VisualEffect;
import com.jothapunkt.spigot.raftcraft.events.ItemAbilityHitEvent;
import com.jothapunkt.spigot.raftcraft.types.DamageType;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class LightningRune extends VisualEffect {
    float rotation = 0f;
    Player caster;

    public LightningRune(Location location, Player caster) {
        super(location);
        duration = 40;
        this.caster = caster;

        BlockDisplay upper = (BlockDisplay) location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
        upper.setTeleportDuration(1);
        upper.setBlock(Material.YELLOW_STAINED_GLASS.createBlockData());
        upper.setTransformation(new Transformation(
            new Vector3f(-.4f, .2f, -.4f),
            new AxisAngle4f(),
            new Vector3f(.8f, .15f, .8f),
            new AxisAngle4f()
        ));
        addPart("upper", upper);

        BlockDisplay lower = (BlockDisplay) location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
        lower.setBlock(Material.YELLOW_GLAZED_TERRACOTTA.createBlockData());
        lower.setTeleportDuration(1);
        lower.setTransformation(new Transformation(
            new Vector3f(-.4f, 0f, -.4f),
            new AxisAngle4f(0, 0, 0, 1),
            new Vector3f(.8f, .15f, .8f),
            new AxisAngle4f(0, 0, 0, 1)
        ));
        addPart("lower", lower);
    }

    @Override
    public void tick() {
        super.tick();

        rotation += getAge();

        Displays.setRotation((Display) getPart("upper"), rotation, 0f);
        Displays.setRotation((Display) getPart("lower"), -rotation, 0f);
    }

    @Override
    public void onExpire() {
        super.onExpire();
        location.getWorld().strikeLightning(location);
        LightningAbility ability = new LightningAbility();

        for (Entity entity : caster.getLocation().getWorld().getEntities()) {
            if (!(entity instanceof Mob)) {
                continue;
            }

            Mob mob = (Mob) entity;

            if (location.distance(entity.getLocation()) < ability.getRange()) {
                ItemAbilityHitEvent e = new ItemAbilityHitEvent(ability, caster, mob);
                Bukkit.getServer().getPluginManager().callEvent(e);
                if (!e.isCancelled()) {
                    MobInfo info = new MobInfo(mob);
                    info.applyKnockback(location, 2.5);
                    info.applyDamage(caster, e.getAbility().getDamage(caster), DamageType.NORMAL);
                }
            }
        }
    }
}
