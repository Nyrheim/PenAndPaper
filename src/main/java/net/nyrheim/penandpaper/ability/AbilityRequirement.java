package net.nyrheim.penandpaper.ability;

public class AbilityRequirement {

    private final Ability ability;
    private final int minimum;

    public AbilityRequirement(Ability ability, int minimum) {
        this.ability = ability;
        this.minimum = minimum;
    }

    public Ability getAbility() {
        return ability;
    }

    public int getMinimum() {
        return minimum;
    }

    @Override
    public String toString() {
        return ">" + getMinimum() + " " + getAbility().getName();
    }
}
