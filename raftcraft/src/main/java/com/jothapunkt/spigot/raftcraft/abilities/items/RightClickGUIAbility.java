package com.jothapunkt.spigot.raftcraft.abilities.items;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.jothapunkt.spigot.raftcraft.abilities.items.generic.RightClickAbility;
import com.jothapunkt.spigot.raftcraft.gui.generic.GUI;

public class RightClickGUIAbility extends RightClickAbility {
    protected Supplier<GUI> guiSupplier;

    public RightClickGUIAbility(Supplier<GUI> guiSupplier) {
        this.guiSupplier = guiSupplier;
    }

    @Override
    public void use(Player player) {
        guiSupplier.get().show(player);
    }
}
