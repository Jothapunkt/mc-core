package com.jothapunkt.spigot.raftcraft.items.fishingrods;

import org.bukkit.entity.FishHook;

import com.jothapunkt.spigot.raftcraft.effects.visual.WebRodEffect;
import com.jothapunkt.spigot.raftcraft.items.generic.FishingRod;

public class WebRod extends FishingRod {
    public WebRod() {
        name = "Web Rod";
    }

    @Override
    public void onWaterHit(FishHook hook) {
        new WebRodEffect(hook);
    }
}
