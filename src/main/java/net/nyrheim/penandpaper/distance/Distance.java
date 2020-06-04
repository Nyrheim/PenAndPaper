package net.nyrheim.penandpaper.distance;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.round;

public final class Distance implements Comparable<Distance> {

    private final double value;
    private final DistanceUnit unit;

    public Distance(double value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public DistanceUnit getUnit() {
        return unit;
    }

    public Distance to(DistanceUnit unit) {
        return new Distance((int) round((getValue() / getUnit().getScaleFactor()) * unit.getScaleFactor()), unit);
    }

    @Override
    public int compareTo(@NotNull Distance distance) {
        return (int) round((getValue() / getUnit().getScaleFactor()) - (distance.getValue() / distance.getUnit().getScaleFactor()));
    }
}
