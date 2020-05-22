package com.github.liamvii.penandpaper.item.armor;

import java.time.Duration;

public enum ArmorCategory {

    LIGHT_ARMOR(
            "Light Armor",
            Duration.ofMinutes(1L),
            Duration.ofMinutes(1L)
    ),
    MEDIUM_ARMOR(
            "Medium Armor",
            Duration.ofMinutes(5L),
            Duration.ofMinutes(1L)
    ),
    HEAVY_ARMOR(
            "Heavy Armor",
            Duration.ofMinutes(10L),
            Duration.ofMinutes(5L)
    ),
    SHIELD(
            "Shield",
            null,
            null
    );

    private final String name;
    private final Duration donTime;
    private final Duration doffTime;

    ArmorCategory(
            String name,
            Duration donTime,
            Duration doffTime
    ) {
        this.name = name;
        this.donTime = donTime;
        this.doffTime = doffTime;
    }

    public String getName() {
        return name;
    }

}
