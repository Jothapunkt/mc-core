package com.jothapunkt.spigot.raftcraft.abilities.items.generic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.PlayerInfo;

import net.md_5.bungee.api.ChatColor;

public class ItemAbility {
    protected String name = "Placeholder";
    protected String type = "Placeholder";
    protected Stat resource = Stat.MANA;
    protected Double cost = 0.0;
    protected Double cooldown = 0.0;
    protected List<String> description = new ArrayList<>();

    public int costRounded() {
        return (int) Math.round(cost);
    }

    public boolean canUse(Player player) {
        // Check resource cost
        if (new PlayerInfo(player).getStat(resource) < cost) {
            new PlayerInfo(player).setActionBarMessage(
                ChatColor.RED + "Need " + costRounded() + " " + resource.getName()
            );
            return false;
        }

        // Check cooldown
        if (cooldown > 0) {
            // Get last use time
            Long lastUse = new PlayerInfo(player).getMeta().getLong("LastUse_" + name);
            if (lastUse == null) {
                lastUse = System.currentTimeMillis() - 999999999;
            }

            // Check if cooldown is expired
            if (System.currentTimeMillis() < lastUse + cooldown * 1000) {
                new PlayerInfo(player).setActionBarMessage(
                    ChatColor.RED + "Cooldown: " + (int) Math.round(((lastUse + cooldown * 1000) - System.currentTimeMillis()) / 1000) + "s"
                );
                return false;
            }
        }

        return true;
    }

    public void subtractCost(Player player) {
        if (cost == 0) {
            return;
        }

        PlayerInfo info = new PlayerInfo(player);
        info.setStat(resource, info.getStat(resource) - cost);

        new PlayerInfo(player).setActionBarMessage(
            resource.getColor() + "-" + costRounded() + " " + resource.getName()
        );
    }

    protected void use(Player player) {

    }

    public void attemptUse(Player player) {
        if (canUse(player)) {
            subtractCost(player);
            // Set last use time for cooldown calculation
            new PlayerInfo(player).getMeta().set("LastUse_" + name, System.currentTimeMillis());
            use(player);
        }
    }

    public List<String> getDescription() {
        List<String> fullDescription = new ArrayList<>();

        fullDescription.add(ChatColor.AQUA + "Ability: " + name + " " + ChatColor.BOLD + type);

        for (String line : description) {
            fullDescription.add(ChatColor.GRAY + line);
        }

        if (cost > 0) {
            fullDescription.add(ChatColor.DARK_GRAY + "Cost: " + resource.getShortName() + " " + cost + " " + resource.getColor() + resource.getName());
        }

        return fullDescription;
    }
}
