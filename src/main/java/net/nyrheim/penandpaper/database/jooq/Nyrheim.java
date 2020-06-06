/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq;


import java.util.Arrays;
import java.util.List;

import net.nyrheim.penandpaper.database.jooq.tables.ActiveCharacter;
import net.nyrheim.penandpaper.database.jooq.tables.Character;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterAbilityScore;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterAbilityScoreChoice;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterClass;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterTempAbilityScore;
import net.nyrheim.penandpaper.database.jooq.tables.Player;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Nyrheim extends SchemaImpl {

    private static final long serialVersionUID = -698806424;

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
     * The table <code>nyrheim.character_ability_score_choice</code>.
     */
    public final CharacterAbilityScoreChoice CHARACTER_ABILITY_SCORE_CHOICE = CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE;

    /**
     * The table <code>nyrheim.character_class</code>.
     */
    public final CharacterClass CHARACTER_CLASS = CharacterClass.CHARACTER_CLASS;

    /**
     * The table <code>nyrheim.character_temp_ability_score</code>.
     */
    public final CharacterTempAbilityScore CHARACTER_TEMP_ABILITY_SCORE = CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE;

    /**
     * The table <code>nyrheim.player</code>.
     */
    public final Player PLAYER = Player.PLAYER;

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
            CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE,
            CharacterClass.CHARACTER_CLASS,
            CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE,
            Player.PLAYER);
    }
}
