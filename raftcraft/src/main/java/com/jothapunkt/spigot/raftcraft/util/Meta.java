package com.jothapunkt.spigot.raftcraft.util;

import java.util.List;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

import com.jothapunkt.spigot.raftcraft.RaftCraft;
import com.jothapunkt.spigot.raftcraft.types.Stat;

public class Meta {
    private Metadatable obj;

    public Meta(Metadatable obj) {
        this.obj = obj;
    }

    public FixedMetadataValue set(String key, Object value) {
        FixedMetadataValue meta = new FixedMetadataValue(RaftCraft.getInstance(), value);
        obj.setMetadata(key, meta);
        return meta;
    }

    public void remove(String key) {
        obj.removeMetadata(key, RaftCraft.getInstance());
    }

    public void setStat(Stat key, Double value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        
        set(key.name(), value);
    }

    public MetadataValue get(String key) {
        List<MetadataValue> values =  obj.getMetadata(key);

        if (values.size() == 0) {
            return null;
        }

        for (MetadataValue value : values) {
            if (value.getOwningPlugin().getName().equals(RaftCraft.getInstance().getName())) {
                return value;
            }
        }

        return null;
    }

    public Double getDouble(String key) {
        MetadataValue value = get(key);

        if (value == null) {
            return null;
        }

        return value.asDouble();
    }

    public Long getLong(String key) {
        MetadataValue value = get(key);

        if (value == null) {
            return null;
        }

        return value.asLong();
    }

    public String getString(String key) {
        MetadataValue value = get(key);

        if (value == null) {
            return null;
        }

        return value.asString();
    }

    public Double getStat(Stat stat) {
        Double value = getDouble(stat.name());

        return value;
    }

    public int getStatRounded(Stat stat) {
        return (int) Math.round(getDouble(stat.name()));
    }

    public void clampStat(Stat stat, Double min, Double max) {
        if (min != null) {
            if (getStat(stat) < min) {
                setStat(stat, min);
            }
        }

        if (max != null) {
            if (getStat(stat) > max) {
                setStat(stat, max);
            }
        }
    }
}
