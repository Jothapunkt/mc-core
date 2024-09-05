package com.jothapunkt.spigot.raftcraft.effects.visual;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Bee;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.abilities.items.IcicleAbility;
import com.jothapunkt.spigot.raftcraft.effects.generic.LineProjectile;
import com.jothapunkt.spigot.raftcraft.modifiers.effects.Frozen;
import com.jothapunkt.spigot.raftcraft.types.DamageType;
import com.jothapunkt.spigot.raftcraft.util.Displays;
import com.jothapunkt.spigot.raftcraft.util.MobInfo;

public class IcicleProjectile extends LineProjectile {
    public IcicleProjectile(Location location, Player caster) {
        super(location, caster);
        speed = 0.5;
        
        Location icicleLocation = location.add(0, 1, 0);
        BlockDisplay icicle = (BlockDisplay) location.getWorld().spawnEntity(icicleLocation, EntityType.BLOCK_DISPLAY);
        
        icicle.setBlock(Material.ICE.createBlockData());
        icicle.setTeleportDuration(1);
        Displays.setScale(icicle, .1f, .1f, 1.5f);

        icicle.setRotation(caster.getYaw(), caster.getPitch());

        addPart("icicle", icicle);
    }

    @Override
    public void tick() {
        super.tick();
        extend(3);
    }

    @Override
    public void onHit(MobInfo target) {
        new FreezeEffect(target.getInstance());
        target.applyDamage(caster, new IcicleAbility().getDamage(caster), DamageType.NORMAL);
        target.applyEffect(new Frozen(new IcicleAbility().getFreezeDuration()));
    }
}
