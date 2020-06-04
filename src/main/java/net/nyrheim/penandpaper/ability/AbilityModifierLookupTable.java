package net.nyrheim.penandpaper.ability;

public final class AbilityModifierLookupTable {

    private AbilityModifierLookupTable() {}

    public static int lookupModifier(int abilityScore) {
        switch (abilityScore) {
            case 1: return -5;
            case 2: case 3: return -4;
            case 4: case 5: return -3;
            case 6: case 7: return -2;
            case 8: case 9: return -1;
            case 10: case 11: return 0;
            case 12: case 13: return 1;
            case 14: case 15: return 2;
            case 16: case 17: return 3;
            case 18: case 19: return 4;
            case 20: case 21: return 5;
            case 22: case 23: return 6;
            case 24: case 25: return 7;
            case 26: case 27: return 8;
            case 28: case 29: return 9;
            case 30: return 10;
            default: return 0;
        }
    }

}
