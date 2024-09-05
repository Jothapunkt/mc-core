package com.jothapunkt.spigot.raftcraft.types;

import java.util.Random;

public class Wind {
    private int windYaw = new Random().nextInt(360);
    private double windStrength = 0.03;

    public int getWindYaw() {
        return windYaw;
    }

    public void setWindYaw(int windYaw) {
        this.windYaw = windYaw;
    }

    public double getWindStrength() {
        return windStrength;
    }

    public void setWindStrength(double windStrength) {
        this.windStrength = windStrength;
    }

    public void randomizedWindChange() {

    }
}
