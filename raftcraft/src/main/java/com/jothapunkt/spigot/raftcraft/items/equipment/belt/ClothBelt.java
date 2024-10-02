package com.jothapunkt.spigot.raftcraft.items.equipment.belt;

import com.jothapunkt.spigot.raftcraft.items.generic.Belt;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class ClothBelt extends Belt {
    public ClothBelt() {
        name = "Cloth Belt";
        rarity = Rarity.COMMON;
        stat(Stat.HEALTH, 10.0);
        baseItem = Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJkMmUzZmNmMGUzOTAzN2VhZjIyMzg0ZGFiM2YwODUyMmE2Yjg5MWQyN2E0N2I3NTI3OTE2ZWE4MWFkZDNjNSJ9fX0=");
    }
}
