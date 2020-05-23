/*
 * This file is generated by jOOQ.
 */
package com.github.liamvii.penandpaper.database.jooq.nyrheim;


import com.github.liamvii.penandpaper.database.jooq.DefaultCatalog;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.tables.ActiveCharacter;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.tables.Character;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.tables.CharacterAbilityScore;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.tables.CharacterClass;
import com.github.liamvii.penandpaper.database.jooq.nyrheim.tables.CharacterTempAbilityScore;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Nyrheim extends SchemaImpl {

    private static final long serialVersionUID = 216236175;

    /**
     * The reference instance of <code>nyrheim</code>
     */
    public static final Nyrheim NYRHEIM = new Nyrheim();

    /**
     * The table <code>nyrheim.active_character</code>.
     */
    public final ActiveCharacter ACTIVE_CHARACTER = ActiveCharacter.ACTIVE_CHARACTER;

    /**
     * The table <code>nyrheim.character</code>.
     */
    public final Character CHARACTER = Character.CHARACTER;

    /**
     * The table <code>nyrheim.character_ability_score</code>.
     */
    public final CharacterAbilityScore CHARACTER_ABILITY_SCORE = CharacterAbilityScore.CHARACTER_ABILITY_SCORE;

    /**
     * The table <code>nyrheim.character_class</code>.
     */
    public final CharacterClass CHARACTER_CLASS = CharacterClass.CHARACTER_CLASS;

    /**
     * The table <code>nyrheim.character_temp_ability_score</code>.
     */
    public final CharacterTempAbilityScore CHARACTER_TEMP_ABILITY_SCORE = CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE;

    /**
     * No further instances allowed
     */
    private Nyrheim() {
        super("nyrheim", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            ActiveCharacter.ACTIVE_CHARACTER,
            Character.CHARACTER,
            CharacterAbilityScore.CHARACTER_ABILITY_SCORE,
            CharacterClass.CHARACTER_CLASS,
            CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE);
    }
}
