package net.nyrheim.penandpaper.ability;

import static net.nyrheim.penandpaper.ability.Ability.STRENGTH;

public class StrengthRequirement extends AbilityRequirement {
    public StrengthRequirement(int minimum) {
        super(STRENGTH, minimum);
    }
}
