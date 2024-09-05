package com.jothapunkt.spigot.raftcraft.effects.visual;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.effects.generic.EntityBoundEffect;
import com.jothapunkt.spigot.raftcraft.logic.FlotsamLogic;

public class WebRodEffect extends EntityBoundEffect {
    protected float size = 2f;
    protected Player caster;

    public WebRodEffect(FishHook hook) {
        super(hook);
        caster = (Player) hook.getShooter();

        ItemDisplay web = (ItemDisplay) location.getWorld().spawnEntity(location, EntityType.ITEM_DISPLAY);
        web.setItemStack(new ItemStack(Material.COBWEB));
        web.setTransformation(new Transformation(
            new Vector3f(0f, 0f, -0.02f),
            new AxisAngle4f(0, 0, 0, 1),
            new Vector3f(size * 2, size * 2, 1f),
            new AxisAngle4f(0, 0, 0, 1)
        ));
        web.setRotation(0f, 90f);
        addPart("web", web);
    }

    @Override
    public void tick() {
        super.tick();
        // Catch nearby flotsam

        for (Entity entity : location.getWorld().getEntities()) {
            if (entity instanceof ItemDisplay) {
                ItemDisplay flotsam = (ItemDisplay) entity;

                // If there is no metadata: Skip
                if (!entity.getPersistentDataContainer().has(new NamespacedKey(RaftCraft.getInstance(), "flotsam_type"))) {
                    continue;
                }

                if (flotsam.getLocation().distance(location) < size) {
                    FlotsamLogic.catchFlotsam(flotsam, caster);
                }
            }
        }
    }
}
