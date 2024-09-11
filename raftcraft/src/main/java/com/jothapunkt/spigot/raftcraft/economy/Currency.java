package com.jothapunkt.spigot.raftcraft.economy;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import com.jothapunkt.spigot.raftcraft.util.ActionResult;
import com.jothapunkt.spigot.raftcraft.util.PersistentData;
import com.jothapunkt.spigot.raftcraft.util.Strings;

public enum Currency {
    COINS,
    SHARDS,
    STARS;

    public String[] getKey() {
        return (String[]) List.of("currency", name()).toArray();
    }

    public String getName() {
        return Strings.snakeToCamelCase(name());
    }

    public Double getBalance(Player player) {
        return PersistentData.from(player).get(PersistentDataType.DOUBLE, getKey());
    }

    public void setBalance(Player player, Double amount) {
        PersistentData.from(player).set(amount, getKey());
    }

    public ActionResult transfer(Player from, Player to, Double amount) {
        if (getBalance(from) < amount) {
            return new ActionResult("Not enough " + getName());
        }
        setBalance(from, getBalance(from) - amount);
        setBalance(to, getBalance(to) + amount);
        return new ActionResult();
    }
}
