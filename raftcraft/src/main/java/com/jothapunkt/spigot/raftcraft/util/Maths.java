package com.jothapunkt.spigot.raftcraft.util;

public class Maths {
    public static double clamp(double number, double min, double max) {
        if (number > max) {
            return max;
        }

        if (number < min) {
            return min;
        }

        return number;
    }

    public static float clamp(float number, float min, float max) {
        if (number > max) {
            return max;
        }

        if (number < min) {
            return min;
        }

        return number;
    }
}
