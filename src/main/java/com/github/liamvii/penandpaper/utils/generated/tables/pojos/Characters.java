/*
 * This file is generated by jOOQ.
 */
package com.github.liamvii.penandpaper.utils.generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Characters implements Serializable {

    private static final long serialVersionUID = -663683298;

    private final String characterid;
    private final String uuid;
    private final String firstname;
    private final String familyname;
    private final String age;
    private final String height;
    private final String weight;
    private final String appearance;
    private final String presence;
    private final String strength;
    private final String dexterity;
    private final String constitution;
    private final String intelligence;
    private final String wisdom;
    private final String charisma;
    private final String experience;

    public Characters(Characters value) {
        this.characterid = value.characterid;
        this.uuid = value.uuid;
        this.firstname = value.firstname;
        this.familyname = value.familyname;
        this.age = value.age;
        this.height = value.height;
        this.weight = value.weight;
        this.appearance = value.appearance;
        this.presence = value.presence;
        this.strength = value.strength;
        this.dexterity = value.dexterity;
        this.constitution = value.constitution;
        this.intelligence = value.intelligence;
        this.wisdom = value.wisdom;
        this.charisma = value.charisma;
        this.experience = value.experience;
    }

    public Characters(
        String characterid,
        String uuid,
        String firstname,
        String familyname,
        String age,
        String height,
        String weight,
        String appearance,
        String presence,
        String strength,
        String dexterity,
        String constitution,
        String intelligence,
        String wisdom,
        String charisma,
        String experience
    ) {
        this.characterid = characterid;
        this.uuid = uuid;
        this.firstname = firstname;
        this.familyname = familyname;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.appearance = appearance;
        this.presence = presence;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.experience = experience;
    }

    public String getCharacterid() {
        return this.characterid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getFamilyname() {
        return this.familyname;
    }

    public String getAge() {
        return this.age;
    }

    public String getHeight() {
        return this.height;
    }

    public String getWeight() {
        return this.weight;
    }

    public String getAppearance() {
        return this.appearance;
    }

    public String getPresence() {
        return this.presence;
    }

    public String getStrength() {
        return this.strength;
    }

    public String getDexterity() {
        return this.dexterity;
    }

    public String getConstitution() {
        return this.constitution;
    }

    public String getIntelligence() {
        return this.intelligence;
    }

    public String getWisdom() {
        return this.wisdom;
    }

    public String getCharisma() {
        return this.charisma;
    }

    public String getExperience() {
        return this.experience;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Characters (");

        sb.append(characterid);
        sb.append(", ").append(uuid);
        sb.append(", ").append(firstname);
        sb.append(", ").append(familyname);
        sb.append(", ").append(age);
        sb.append(", ").append(height);
        sb.append(", ").append(weight);
        sb.append(", ").append(appearance);
        sb.append(", ").append(presence);
        sb.append(", ").append(strength);
        sb.append(", ").append(dexterity);
        sb.append(", ").append(constitution);
        sb.append(", ").append(intelligence);
        sb.append(", ").append(wisdom);
        sb.append(", ").append(charisma);
        sb.append(", ").append(experience);

        sb.append(")");
        return sb.toString();
    }
}
