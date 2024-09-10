package com.jothapunkt.spigot.raftcraft.effects.visual;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import com.jothapunkt.spigot.raftcraft.effects.generic.EntityBoundEffect;
import com.jothapunkt.spigot.raftcraft.entities.mobs.MobRegistry;
import com.jothapunkt.spigot.raftcraft.logic.FlotsamLogic;

public class CustomMobNameTag extends EntityBoundEffect {

    public CustomMobNameTag(Mob mob) {
        super(mob);

        // Set to be permanent as long as the mob is alive
        duration = -1;

        // Set text display to hover above bounding box
        location.setY(mob.getBoundingBox().getMaxY() + 0.3);

        TextDisplay nameTag = (TextDisplay) mob.getLocation().getWorld().spawnEntity(location, EntityType.TEXT_DISPLAY);
        nameTag.setBillboard(Billboard.CENTER);
        nameTag.setTeleportDuration(2);
        addPart("nameTag", nameTag);
    }

    @Override
    public void tick() {
        super.tick();

        // Update name text
        TextDisplay nameTag = (TextDisplay) getPart("nameTag");
        String text = MobRegistry.get(Bukkit.getEntity(entityUuid)).getInstanceName((Mob) Bukkit.getEntity(entityUuid));
        nameTag.setText(text);
    }
}
