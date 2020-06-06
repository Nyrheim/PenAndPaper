/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq.tables.records;


import net.nyrheim.penandpaper.database.jooq.tables.CharacterAbilityScore;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CharacterAbilityScoreRecord extends UpdatableRecordImpl<CharacterAbilityScoreRecord> implements Record3<Integer, String, Integer> {

    private static final long serialVersionUID = -1272846433;

    /**
     * Setter for <code>nyrheim.character_ability_score.character_id</code>.
     */
    public void setCharacterId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>nyrheim.character_ability_score.character_id</code>.
     */
    public Integer getCharacterId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>nyrheim.character_ability_score.ability</code>.
     */
    public void setAbility(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>nyrheim.character_ability_score.ability</code>.
     */
    public String getAbility() {
        return (String) get(1);
    }

    /**
     * Setter for <code>nyrheim.character_ability_score.score</code>.
     */
    public void setScore(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>nyrheim.character_ability_score.score</code>.
     */
    public Integer getScore() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CharacterAbilityScore.CHARACTER_ABILITY_SCORE.CHARACTER_ID;
    }

    @Override
    public Field<String> field2() {
        return CharacterAbilityScore.CHARACTER_ABILITY_SCORE.ABILITY;
    }

    @Override
    public Field<Integer> field3() {
        return CharacterAbilityScore.CHARACTER_ABILITY_SCORE.SCORE;
    }

    @Override
    public Integer component1() {
        return getCharacterId();
    }

    @Override
    public String component2() {
        return getAbility();
    }

    @Override
    public Integer component3() {
        return getScore();
    }

    @Override
    public Integer value1() {
        return getCharacterId();
    }

    @Override
    public String value2() {
        return getAbility();
    }

    @Override
    public Integer value3() {
        return getScore();
    }

    @Override
    public CharacterAbilityScoreRecord value1(Integer value) {
        setCharacterId(value);
        return this;
    }

    @Override
    public CharacterAbilityScoreRecord value2(String value) {
        setAbility(value);
        return this;
    }

    @Override
    public CharacterAbilityScoreRecord value3(Integer value) {
        setScore(value);
        return this;
    }

    @Override
    public CharacterAbilityScoreRecord values(Integer value1, String value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CharacterAbilityScoreRecord
     */
    public CharacterAbilityScoreRecord() {
        super(CharacterAbilityScore.CHARACTER_ABILITY_SCORE);
    }

    /**
     * Create a detached, initialised CharacterAbilityScoreRecord
     */
    public CharacterAbilityScoreRecord(Integer characterId, String ability, Integer score) {
        super(CharacterAbilityScore.CHARACTER_ABILITY_SCORE);

        set(0, characterId);
        set(1, ability);
        set(2, score);
    }
}
