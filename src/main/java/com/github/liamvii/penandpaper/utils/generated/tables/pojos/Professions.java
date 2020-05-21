/*
 * This file is generated by jOOQ.
 */
package com.github.liamvii.penandpaper.utils.generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Professions implements Serializable {

    private static final long serialVersionUID = 2144043746;

    private final String professionid;
    private final String characterid;
    private final String profession;
    private final String level;

    public Professions(Professions value) {
        this.professionid = value.professionid;
        this.characterid = value.characterid;
        this.profession = value.profession;
        this.level = value.level;
    }

    public Professions(
        String professionid,
        String characterid,
        String profession,
        String level
    ) {
        this.professionid = professionid;
        this.characterid = characterid;
        this.profession = profession;
        this.level = level;
    }

    public String getProfessionid() {
        return this.professionid;
    }

    public String getCharacterid() {
        return this.characterid;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getLevel() {
        return this.level;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Professions (");

        sb.append(professionid);
        sb.append(", ").append(characterid);
        sb.append(", ").append(profession);
        sb.append(", ").append(level);

        sb.append(")");
        return sb.toString();
    }
}
