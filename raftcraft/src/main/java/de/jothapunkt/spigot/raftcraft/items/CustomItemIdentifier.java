package com.jothapunkt.spigot.raftcraft.items;

import com.jothapunkt.spigot.raftcraft.items.materials.PalmLeaf;
import com.jothapunkt.spigot.raftcraft.items.materials.Plank;
import com.jothapunkt.spigot.raftcraft.items.materials.Thatch;
import com.jothapunkt.spigot.raftcraft.items.wands.JumpFeather;
import com.jothapunkt.spigot.raftcraft.items.wands.WarpStick;
import com.jothapunkt.spigot.raftcraft.items.armor.SpeedBoots;
import com.jothapunkt.spigot.raftcraft.items.generic.CustomItem;
import com.jothapunkt.spigot.raftcraft.items.armor.SailorsHat;
import com.jothapunkt.spigot.raftcraft.items.wands.HurtWand;
import com.jothapunkt.spigot.raftcraft.items.catalysts.ArcaneEye;
import com.jothapunkt.spigot.raftcraft.items.wands.LightningWand;
import com.jothapunkt.spigot.raftcraft.items.fishingrods.WebRod;
import com.jothapunkt.spigot.raftcraft.items.wands.BeeWand;
import com.jothapunkt.spigot.raftcraft.items.swords.Claymore;
import com.jothapunkt.spigot.raftcraft.items.wands.IceStaff;
import com.jothapunkt.spigot.raftcraft.items.blocks.PreservingJar;


public enum CustomItemIdentifier {
    ARCANE_EYE(new ArcaneEye()),
    BEE_WAND(new BeeWand()),
    CLAYMORE(new Claymore()),
    HURT_WAND(new HurtWand()),
    ICE_STAFF(new IceStaff()),
    JUMP_FEATHER(new JumpFeather()),
    LIGHTNING_WAND(new LightningWand()),
    PALM_LEAF(new PalmLeaf()),
    PLANK(new Plank()),
    PRESERVING_JAR(new PreservingJar()),
    SAILORS_HAT(new SailorsHat()),
    SPEED_BOOTS(new SpeedBoots()),
    THATCH(new Thatch()),
    WARP_STICK(new WarpStick()),
    WEB_ROD(new WebRod());

    private CustomItem item;

    private CustomItemIdentifier(CustomItem item) {
        this.item = item;
    }

    public CustomItem getItem() {
        return item;
    }

    public static CustomItemIdentifier getIdentifier(CustomItem item) {
        for (CustomItemIdentifier identifier : values()) {
            if (item.getClass().getName().equals(identifier.getItem().getClass().getName())) {
                return identifier;
            }
        }

        return null;
    }
}
