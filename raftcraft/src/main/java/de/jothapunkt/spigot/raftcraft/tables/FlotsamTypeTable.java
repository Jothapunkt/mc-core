package com.jothapunkt.spigot.raftcraft.tables;

import com.jothapunkt.spigot.raftcraft.util.WeightedTable;
import com.jothapunkt.spigot.raftcraft.util.WeightedOption;
import com.jothapunkt.spigot.raftcraft.types.FlotsamType;

import java.util.List;

public class FlotsamTypeTable extends WeightedTable<FlotsamType> {
    public FlotsamTypeTable() {
        options = List.of(
            new WeightedOption<FlotsamType>(FlotsamType.THATCH, 1000),
            new WeightedOption<FlotsamType>(FlotsamType.PALM_LEAF, 1000),
            new WeightedOption<FlotsamType>(FlotsamType.PLANK, 1000),
            new WeightedOption<FlotsamType>(FlotsamType.BARREL, 300),
            new WeightedOption<FlotsamType>(FlotsamType.POT, 300)
        );
    }
}