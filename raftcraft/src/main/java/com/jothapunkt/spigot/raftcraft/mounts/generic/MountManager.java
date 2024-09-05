package com.jothapunkt.spigot.raftcraft.mounts.generic;

public class MountManager {
    private static MountManager instance = null;

    public static MountManager getInstance() {
        if (instance == null) {
            instance = new MountManager();
        }
        return instance;
    }
}
