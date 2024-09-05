package com.jothapunkt.spigot.raftcraft.util;

import java.awt.Color;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Billboard;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.types.DamageType;

import net.md_5.bungee.api.ChatColor;

public class Displays {
    public static TextDisplay damageDisplay(Location location, Double amount, DamageType type) {        
        if (type == DamageType.CRIT) {
            return addTemporaryText(
                location,
                Colors.colorLoopText("✸ " + (int) Math.round(amount),
                List.of(
                    new Color(255, 153, 51),
                    new Color(255, 102, 0),
                    new Color(255, 80, 80),
                    new Color(230, 230, 230),
                    new Color(255, 255, 0),
                    new Color(255, 204, 0)
                )),
                3
            );
        }

        return addTemporaryText(location, ChatColor.GRAY + "" + (int) Math.round(amount), 3);
    }

    public static TextDisplay addTemporaryText(Location location, String text, int duration) {
        TextDisplay display = (TextDisplay) location.getWorld().spawnEntity(location, EntityType.TEXT_DISPLAY);
        
        display.setText(ChatColor.GRAY + text);

        display.setBillboard(Billboard.CENTER);

        // Set expiration time
        display.getPersistentDataContainer().set(
            new NamespacedKey(RaftCraft.getInstance(), "expirationTime"),
            PersistentDataType.LONG,
            System.currentTimeMillis() + (duration * 1000)
        );

        return display;
    }

    public static void setRotation(Display display, float yaw, float pitch) {
        display.setRotation(yaw, pitch);
    }

    public static void setLeftRotation(Display display, float x, float y, float z, float degrees) {
        Transformation old = display.getTransformation();
        Transformation temp = new Transformation(old.getTranslation(), new AxisAngle4f((float) Math.toRadians(degrees), x, y, z), old.getScale(), new AxisAngle4f(0, 0, 0, 1));
        display.setTransformation(
            new Transformation(old.getTranslation(), temp.getLeftRotation(), old.getScale(), old.getRightRotation())
        );
    }

    public static void setRightRotation(Display display, float x, float y, float z, float degrees) {
        Transformation old = display.getTransformation();
        Transformation temp = new Transformation(old.getTranslation(), new AxisAngle4f(0, 0, 0, 1), old.getScale(), new AxisAngle4f((float) Math.toRadians(degrees), x, y, z));
        display.setTransformation(
            new Transformation(old.getTranslation(), old.getLeftRotation(), old.getScale(), temp.getRightRotation())
        );
    }

    public static void setScale(Display display, float x, float y, float z) {
        Transformation old = display.getTransformation();
        display.setTransformation(
            new Transformation(old.getTranslation(), old.getLeftRotation(), new Vector3f(x, y, z), old.getRightRotation())
        );
    }

    public static void setTranslation(Display display, float x, float y, float z) {
        Transformation old = display.getTransformation();
        display.setTransformation(
            new Transformation(new Vector3f(x, y, z), old.getLeftRotation(), old.getScale(), old.getRightRotation())
        );
    }
}
