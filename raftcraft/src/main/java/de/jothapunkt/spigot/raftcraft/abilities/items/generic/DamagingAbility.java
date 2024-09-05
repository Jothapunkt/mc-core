package com.jothapunkt.spigot.raftcraft.abilities.items.generic;

import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

public interface DamagingAbility {
    public double getBaseDamage();

    default double getManaScaling() {
        return 1.0;
    }

    default double getAbilityDamageScaling() {
        return 1.0;
    }

    default double getDamage(Player caster) {
        PlayerInfo info = new PlayerInfo(caster);
        return getBaseDamage() * Math.max(1.0, info.getStat(Stat.MAX_MANA) * getManaScaling() / 100) * Math.max(1.0, info.getStat(Stat.ABILITY_DAMAGE) * getAbilityDamageScaling() / 100);
    }
}
