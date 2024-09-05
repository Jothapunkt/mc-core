package com.jothapunkt.spigot.raftcraft.types;

import com.jothapunkt.spigot.raftcraft.skills.Skill;
import com.jothapunkt.spigot.raftcraft.util.Strings;

public enum Skills {
    MINING("⛏"),
    FISHING("🎣"),
    FARMING("🌾"),
    SCAVENGING(""),
    DIGGING("🕳"),
    BOATING("⚓"),
    COMBAT("🗡"),
    SWORDS("🗡"),
    AXES("🪓"),
    BOWS(""),
    WANDS("🪄"),
    STAFFS("🔱"),
    DESTRUCTION("🔥"),
    HUSBANDRY("🐂");

    protected String icon;
    protected Skill skill;

    private Skills(String icon) {
        this.icon = icon;
        this.skill = new Skill(this);
    }

    public String getLongName() {
        return icon + " " + Strings.snakeToCamelCase(name());
    }

    public Skill getSkill() {
        return skill;
    }

    public String getIcon() {
        return icon;
    }

    public String getSkillKey() {
        return "SKILL_XP_" + name();
    }
}
