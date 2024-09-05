package com.jothapunkt.spigot.raftcraft.items.catalysts;

import com.jothapunkt.spigot.raftcraft.items.generic.Catalyst;
import com.jothapunkt.spigot.raftcraft.types.Rarity;
import com.jothapunkt.spigot.raftcraft.types.Stat;
import com.jothapunkt.spigot.raftcraft.util.Items;

public class ArcaneEye extends Catalyst {
    public ArcaneEye() {
        name = "Arcane Eye";
        baseItem = Items.head("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzlmOWI5NDVjY2U1MjEwZWUzMDI5MDVlOTBlNDgwZWI2MDAzMDgxMTRiYmE1MDg0YTA0NjcyOTA1NWJjYTI3MiJ9fX0=");
        rarity = Rarity.EPIC;

        stat(Stat.MAX_MANA, 50.0);
        stat(Stat.CRIT_CHANCE, 30.0);
    }
}
