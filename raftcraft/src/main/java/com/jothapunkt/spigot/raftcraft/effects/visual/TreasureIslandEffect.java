package com.jothapunkt.spigot.raftcraft.effects.visual;

import java.util.List;
import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Interaction;
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
import com.jothapunkt.spigot.raftcraft.util.Displays;

public class TreasureIslandEffect extends EntityBoundEffect {

    public TreasureIslandEffect(Interaction core) {
        super(core);

        BlockDisplay ground = (BlockDisplay) location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
        ground.setBlock(Material.SAND.createBlockData());
        ground.setTeleportDuration(1);
        Displays.setScale(ground, 3f, 1f, 3f);
        Displays.setTranslation(ground, -1.5f, 0f, -1.5f);
        addPart("ground", ground);

        BlockDisplay palmTrunk = (BlockDisplay) location.getWorld().spawnEntity(location, EntityType.BLOCK_DISPLAY);
        palmTrunk.setBlock(Material.JUNGLE_LOG.createBlockData());
        palmTrunk.setTeleportDuration(1);
        Displays.setScale(palmTrunk, .3f, 4f, .3f);
        Displays.setTranslation(palmTrunk, .5f, .8f, .5f);
        palmTrunk.setRotation(5, 5);
        addPart("palmTrunk", palmTrunk);

        Location shellLocation = location.clone().add(-.5, 1.05, -.5);
        ItemDisplay shell = (ItemDisplay) location.getWorld().spawnEntity(shellLocation, EntityType.ITEM_DISPLAY);
        shell.setItemStack(new ItemStack(Material.NAUTILUS_SHELL));
        shell.setTeleportDuration(2);
        shell.setRotation(30, -90);
        addPart("shell", shell);

        List<Float> leafRotations = List.of(20f, 50f, -60f, -110f);

        for (int i = 0; i < leafRotations.size(); i++) {
            Location leafLocation = location.clone();
            leafLocation.add(.6f, 4.7f, 1.05f + (i * .01f));

            ItemDisplay leaf = (ItemDisplay) location.getWorld().spawnEntity(leafLocation, EntityType.ITEM_DISPLAY);
            leaf.setItemStack(new ItemStack(Material.KELP));
            Displays.setScale(leaf, 1.5f, 1.5f, 6f);
            Displays.setLeftRotation(leaf, 0, 1, 0, 90);
            Displays.setRotation(leaf, 5f, leafRotations.get(i));

            Location direction = leaf.getLocation().clone();

            direction.setYaw(leafLocation.getYaw());
            direction.setPitch(leafRotations.get(i) - 45);
            leafLocation.add(direction.getDirection().normalize().multiply(.75));
            leafLocation.setYaw(5);
            leafLocation.setPitch(leafRotations.get(i));

            leaf.teleport(leafLocation);
            addPart("leaf" + i, leaf);
        }
    }

    @Override
    public void tick() {
        super.tick();
        extend(5);
    }
}
