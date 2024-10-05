package com.jothapunkt.spigot.raftcraft.types;
import com.jothapunkt.spigot.raftcraft.util.Strings;
import net.md_5.bungee.api.ChatColor;

public enum Stat {
    MAX_HEALTH("♥", ChatColor.RED, 100),
    HEALTH("♥", ChatColor.RED, 100, false),
    HEALTH_REGEN("➕", ChatColor.RED, 1),
    ARMOR("🛡", ChatColor.GRAY, 0),
    SHIELD("🛡", ChatColor.GOLD, 0),
    ATTACK("🗡", ChatColor.DARK_RED, 1),
    CRIT_CHANCE("💥", ChatColor.DARK_GRAY, 20),
    CRIT_DAMAGE("🔥", ChatColor.DARK_GRAY, 50),
    MAX_MANA("✨", ChatColor.BLUE, 100),
    MANA("✨", ChatColor.BLUE, 100, false),
    MANA_REGEN("💧", ChatColor.BLUE, 2),
    ABILITY_DAMAGE("🗲", ChatColor.DARK_PURPLE, 100),
    SPEED("🦶", ChatColor.WHITE, 100),
    SWIM_SPEED("🐠", ChatColor.AQUA, 100),
    BOAT_SPEED("⛵", ChatColor.AQUA, 100),
    PICKUP_RANGE("◯", ChatColor.GREEN, 2),

    MINING_SPEED("◯", ChatColor.GREEN, 100),
    DIGGING_SPEED("◯", ChatColor.GREEN, 100),

    TREASURE_CHANCE("◯", ChatColor.GOLD, 100);

    private Double defaultValue;
    private String icon = "";
    private ChatColor color = ChatColor.WHITE;
    // Static attributes don't change from tick to tick and are recalculated frequently, e.g. Armor
    // Non-static attributes are e.g. Health, which can change a lot and should not be recalculated
    private boolean isStatic = true;

    private Stat(String icon, ChatColor color, int defaultValue) {
        this.icon = icon;
        this.color = color;
        this.defaultValue = Double.valueOf(defaultValue);
    }

    private Stat(String icon, ChatColor color, int defaultValue, boolean isStatic) {
        this.icon = icon;
        this.color = color;
        this.defaultValue = Double.valueOf(defaultValue);
        this.isStatic = isStatic;
    }

    public String key() {
        return name().toLowerCase();
    }

    public String getIcon() {
        return icon;
    }

    public ChatColor getColor() {
        return color;
    }

    public boolean getStatic() {
        return isStatic;
    }

    public String getLongName() {
        return color + icon + " " + Strings.snakeToCamelCase(name());
    }

    public String getName() {
        return Strings.snakeToCamelCase(name());
    }

    public String getShortName() {
        return color + icon;
    }

    public Double getDefaultValue() {
        return defaultValue;
    }
}
