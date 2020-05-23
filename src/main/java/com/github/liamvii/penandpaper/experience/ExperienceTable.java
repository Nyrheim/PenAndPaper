package com.github.liamvii.penandpaper.experience;

public final class ExperienceTable {

    private ExperienceTable() {}

    public static int getExperienceRequiredForLevel(int level) {
        switch (level) {
            case 1: return 0;
            case 2: return 300;
            case 3: return 900;
            case 4: return 2700;
            case 5: return 6500;
            case 6: return 14000;
            case 7: return 23000;
            case 8: return 34000;
            case 9: return 48000;
            case 10: return 64000;
            case 11: return 85000;
            case 12: return 100000;
            case 13: return 120000;
            case 14: return 140000;
            case 15: return 165000;
            case 16: return 195000;
            case 17: return 225000;
            case 18: return 265000;
            case 19: return 305000;
            case 20: return 355000;
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
