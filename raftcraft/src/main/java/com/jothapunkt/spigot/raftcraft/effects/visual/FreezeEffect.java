package com.jothapunkt.spigot.raftcraft.effects.visual;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.effects.generic.EntityBoundEffect;
import com.jothapunkt.spigot.raftcraft.logic.FlotsamLogic;
import com.jothapunkt.spigot.raftcraft.modifiers.effects.Frozen;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class FreezeEffect extends EntityBoundEffect {
    public FreezeEffect(Mob mob) {
        super(mob);

        BlockDisplay ice = (BlockDisplay) location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
        ice.setTeleportDuration(1);
        Displays.setScale(
            ice, 
            (float) mob.getBoundingBox().getWidthX(), 
            (float) mob.getBoundingBox().getHeight(), 
            (float) mob.getBoundingBox().getWidthZ()
        );

        Displays.setTranslation(ice, -0.5f * (float) mob.getBoundingBox().getWidthX(), 0f, -0.5f * (float) mob.getBoundingBox().getWidthZ());

        ice.setRotation(mob.getBodyYaw(), 0);
        ice.setBlock(Material.ICE.createBlockData());
        addPart("ice", ice);
    }

    @Override
    public void tick() {
        super.tick();
        extend(3);
        if (Bukkit.getEntity(entityUuid) != null) {
            Mob mob = (Mob) Bukkit.getEntity(entityUuid);
            getPart("ice").teleport(mob.getLocation());
            getPart("ice").setRotation(mob.getBodyYaw(), 0);

            if (!new MobInfo(mob).hasEffect(Frozen.class)) {
                // If mob is no longer frozen for any reason, destroy the visual and spawn particles
                onExpire();
            }
        }
    }

    @Override
    public void onExpire() {
        super.onExpire();
        location.getWorld().spawnParticle(Particle.BLOCK, location.clone().add(0, 1, 0), 20, 0.5, 0.5, 0.5, Material.ICE.createBlockData());
    }
}
