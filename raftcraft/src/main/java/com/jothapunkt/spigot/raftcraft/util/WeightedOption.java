package com.jothapunkt.spigot.raftcraft.util;

public class WeightedOption<T> {
    protected int weight;
    protected T option;

    public WeightedOption(T option, int weight) {
        this.weight = weight;
        this.option = option;
    }

    public int getWeight() {
        return weight;
    }

    public T getOption() {
        return option;
    }
}