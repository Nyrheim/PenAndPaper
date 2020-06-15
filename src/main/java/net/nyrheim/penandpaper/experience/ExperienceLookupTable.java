package net.nyrheim.penandpaper.experience;

public final class ExperienceLookupTable {

    private ExperienceLookupTable() {}

    public static final int MAX_EXPERIENCE = 45000;

    public static int getExperienceRequiredForLevel(int level) {
        switch (level) {
            case 1: return 0;
            case 2: return 1000;
            case 3: return 3000;
            case 4: return 6000;
            case 5: return 10000;
            case 6: return 15000;
            case 7: return 21000;
            case 8: return 28000;
            case 9: return 36000;
            case 10: return 45000;
            default: return Integer.MAX_VALUE;
        }
    }

    public static int getLevelAtExperience(int experience) {
        int level = 1;
        while (getExperienceRequiredForLevel(level + 1) <= experience) {
            level++;
        }
        return level;
    }
}
