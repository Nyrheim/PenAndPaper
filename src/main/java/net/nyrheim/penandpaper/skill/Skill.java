package net.nyrheim.penandpaper.skill;

import net.nyrheim.penandpaper.ability.Ability;

import static net.nyrheim.penandpaper.ability.Ability.*;

public enum Skill {

    ATHLETICS("Athletics", STRENGTH),
    ACROBATICS("Acrobatics", DEXTERITY),
    SLEIGHT_OF_HAND("Sleight of Hand", DEXTERITY),
    STEALTH("Stealth", DEXTERITY),
    ARCANA("Arcana", INTELLIGENCE),
    HISTORY("History", INTELLIGENCE),
    INVESTIGATION("Investigation", INTELLIGENCE),
    NATURE("Nature", INTELLIGENCE),
    RELIGION("Religion", INTELLIGENCE),
    ANIMAL_HANDLING("Animal Handling", WISDOM),
    INSIGHT("Insight", WISDOM),
    MEDICINE("Medicine", WISDOM),
    PERCEPTION("Perception", WISDOM),
    SURVIVAL("Survival", WISDOM),
    DECEPTION("Deception", CHARISMA),
    INTIMIDATION("Intimidation", CHARISMA),
    PERFORMANCE("Performance", CHARISMA),
    PERSUASION("Persuasion", CHARISMA);

    private final String name;
    private final Ability ability;

    Skill(String name, Ability ability) {
        this.name = name;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public Ability getAbility() {
        return ability;
    }

}
