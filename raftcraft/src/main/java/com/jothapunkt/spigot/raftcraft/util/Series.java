package com.jothapunkt.spigot.raftcraft.util;

import java.util.ArrayList;
import java.util.List;

public class Series {
    public static List<Integer> range(int start, int stop, int step) {
        List<Integer> values = new ArrayList<>();

        for (int i = start; i < stop; i += step) {
            values.add(i);
        }

        return values;
    }

    public static <T> List<T> flat(T value, int length) {
        
        List<T> values = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            values.add(value);
        }

        return values;
    }
}
