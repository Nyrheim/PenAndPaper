package com.github.liamvii.penandpaper.ability;

public final class AbilityScoreCostLookupTable {

    public static final int MAX_ABILITY_COST = 27;

    private AbilityScoreCostLookupTable() {}

    public static int getAbilityScoreCost(int abilityScore) {
        switch (abilityScore) {
            case 8: return 0;
            case 9: return 1;
            case 10: return 2;
            case 11: return 3;
            case 12: return 4;
            case 13: return 5;
            case 14: return 7;
            case 15: return 9;
            default: return 0;
        }
    }

}
