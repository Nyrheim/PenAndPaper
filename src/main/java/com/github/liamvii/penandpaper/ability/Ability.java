package com.github.liamvii.penandpaper.ability;

import java.util.Arrays;

public enum Ability {

    STRENGTH("Strength", "Measuring physical power", "STR"),
    DEXTERITY("Dexterity", "Measuring agility", "DEX"),
    CONSTITUTION("Constitution", "Measuring endurance", "CON"),
    INTELLIGENCE("Intelligence", "Measuring reasonining and memory", "INT"),
    WISDOM("Wisdom", "Measuring perception and insight", "WIS"),
    CHARISMA("Charisma", "Measuring force of personality", "CHA");

    private final String name;
    private final String description;
    private final String abbreviation;

    Ability(String name, String description, String abbreviation) {
        this.name = name;
        this.description = description;
        this.abbreviation = abbreviation;
    }

    public static Ability getByAbbreviation(String abbreviation) {
        return Arrays.stream(values())
                .filter(ability -> ability.getAbbreviation().equalsIgnoreCase(abbreviation))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
