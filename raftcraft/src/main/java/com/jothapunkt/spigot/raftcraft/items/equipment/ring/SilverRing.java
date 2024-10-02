package com.jothapunkt.spigot.raftcraft.items.equipment.ring;

import com.jothapunkt.spigot.raftcraft.items.generic.Ring;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class SilverRing extends Ring {
    public SilverRing() {
        name = "Silver Ring";
        rarity = Rarity.UNCOMMON;
        stat(Stat.MAX_MANA, 20.0);
        baseItem = Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmI0YWI2NTc0ZTFjYzlmMGI4ZmUxNmM3YWNmNWY3ODQyMzEwYThjNzQxYTUzYjRiY2U4YzU2MzRkYmYwOTAzZSJ9fX0=");
    }
}
