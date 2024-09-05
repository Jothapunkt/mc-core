package com.jothapunkt.spigot.raftcraft.mobs.generic;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Strings;

public class VanillaMob extends CustomMob {
    public VanillaMob(EntityType type) {
        this.type = type;
        this.name = Strings.snakeToCamelCase(type.name());
        stat(Stat.HEALTH, type.getDefaultAttributes().getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
    }
}
