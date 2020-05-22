package com.github.liamvii.penandpaper.distance;

public final class Distance {

    private final int value;
    private final DistanceUnit unit;

    public Distance(int value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public DistanceUnit getUnit() {
        return unit;
    }

}
