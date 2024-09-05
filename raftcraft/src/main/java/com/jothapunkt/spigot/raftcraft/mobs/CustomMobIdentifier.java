package com.jothapunkt.spigot.raftcraft.mobs;

import com.jothapunkt.spigot.raftcraft.mobs.generic.CustomMob;
import com.jothapunkt.spigot.raftcraft.mobs.swamp.SwampKnight;
import com.jothapunkt.spigot.raftcraft.mobs.swamp.Wisp;
import com.jothapunkt.spigot.raftcraft.mobs.misc.Dummy;
import com.jothapunkt.spigot.raftcraft.mobs.ice.Yeti;
import com.jothapunkt.spigot.raftcraft.mobs.ice.Iceologer;
import com.jothapunkt.spigot.raftcraft.mobs.ice.IceKing;
import com.jothapunkt.spigot.raftcraft.mobs.ice.FrozenAdventurer;
import com.jothapunkt.spigot.raftcraft.mobs.statues.SoldierStatue;
import com.jothapunkt.spigot.raftcraft.mobs.bamboo.Pandassassin;
import com.jothapunkt.spigot.raftcraft.mobs.ice.WalrusWarrior;
import com.jothapunkt.spigot.raftcraft.mobs.fishing.InverseMermaid;

public enum CustomMobIdentifier {
    DUMMY(new Dummy()),
    FROZEN_ADVENTURER(new FrozenAdventurer()),
    ICEOLOGER(new Iceologer()),
    ICE_KING(new IceKing()),
    INVERSE_MERMAID(new InverseMermaid()),
    PANDASSASSIN(new Pandassassin()),
    SOLDIER_STATUE(new SoldierStatue()),
    SWAMP_KNIGHT(new SwampKnight()),
    WALRUS_WARRIOR(new WalrusWarrior()),
    WISP(new Wisp()),
    YETI(new Yeti());

    private CustomMob mob;

    private CustomMobIdentifier(CustomMob mob) {
        this.mob = mob;
    }

    public CustomMob getMob() {
        return mob;
    }

    public static CustomMobIdentifier getIdentifier(CustomMob mob) {
        for (CustomMobIdentifier identifier : values()) {
            if (mob.getClass().getName().equals(identifier.getMob().getClass().getName())) {
                return identifier;
            }
        }

        return null;
    }
}
