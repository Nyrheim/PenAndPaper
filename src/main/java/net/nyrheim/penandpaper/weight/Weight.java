package net.nyrheim.penandpaper.weight;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.round;

public final class Weight implements Comparable<Weight> {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public Weight to(WeightUnit unit) {
        return new Weight((getValue() / getUnit().getScaleFactor()) * unit.getScaleFactor(), unit);
    }

    public Weight multiply(int amount) {
        return new Weight(getValue() * amount, getUnit());
    }

    @Override
    public int compareTo(@NotNull Weight weight) {
        return (int) round((getValue() / getUnit().getScaleFactor()) - (weight.getValue() / weight.getUnit().getScaleFactor()));
    }

    @Override
    public String toString() {
        if (getValue() == (long) getValue()) {
            return String.format("%.0f%s", getValue(), getUnit().getName());
        } else {
            return String.format("%s%s", getValue(), getUnit().getName());
        }
    }
}
