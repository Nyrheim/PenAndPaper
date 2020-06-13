package net.nyrheim.penandpaper.ability;

public class ProficiencyBonusLookupTable {

    public static int proficiencyBonusLookup(int level) {
        switch (level) {
            case 1: case 2: case 3: case 4: return 2;
            case 5: case 6: case 7: case 8: return 3;
            case 9: case 10: return 4;
            default: return 0;
        }
    }
}
