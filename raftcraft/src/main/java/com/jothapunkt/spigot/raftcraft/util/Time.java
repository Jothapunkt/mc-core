package com.jothapunkt.spigot.raftcraft.util;

import java.util.List;


public class Time {
    public static Long EPOCH = 1704067200000L;
    public static List<String> MONTH_NAMES = List.of(
        "Spring",
        "Summer",
        "Autumn",
        "Winter"
    );
    public static int TICKS_PER_MINUTE = 20;
    public static int TICKS_PER_HOUR = 60 * TICKS_PER_MINUTE;
    public static int TICKS_PER_DAY = 24 * TICKS_PER_HOUR;
    public static int TICKS_PER_MONTH = TICKS_PER_DAY * 30;
    public static int TICKS_PER_YEAR = TICKS_PER_MONTH * MONTH_NAMES.size();

    private Long time;
    private int ticksSinceEpoch;

    public Time(Long time) {
        this.time = time;
        this.ticksSinceEpoch = (int) ((time - EPOCH) / 50); // 50ms per tick at 20tps
    }

    public static Time now() {
        return new Time(System.currentTimeMillis());
    }

    public int getTimeOfDay() {
        return ticksSinceEpoch % TICKS_PER_DAY;
    }

    public int getYear() {
        return (int) (ticksSinceEpoch / TICKS_PER_YEAR);
    }

    public int getMonth() {
        return (int) ((ticksSinceEpoch % TICKS_PER_YEAR) / TICKS_PER_MONTH) + 1;
    }

    public int getDate() {
        return (int) ((ticksSinceEpoch % TICKS_PER_MONTH) / TICKS_PER_DAY);
    }

    public int getHour() {
        return (int) ((ticksSinceEpoch % TICKS_PER_DAY) / TICKS_PER_HOUR);
    }

    public int getMinute() {
        return (int) ((ticksSinceEpoch % TICKS_PER_HOUR) / TICKS_PER_MINUTE);
    }
}
