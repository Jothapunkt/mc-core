package com.jothapunkt.spigot.raftcraft.types;

public enum Difficulty {
    NOVICE(1),
    APPRENTICE(2),
    ADEPT(3),
    EXPERT(4),
    MASTER(5);

    private int value;
    private Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
