/*
 * This file is generated by jOOQ.
 */
package net.nyrheim.penandpaper.database.jooq;


import net.nyrheim.penandpaper.database.jooq.tables.ActiveCharacter;
import net.nyrheim.penandpaper.database.jooq.tables.Character;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterAbilityScore;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterAbilityScoreChoice;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterClass;
import net.nyrheim.penandpaper.database.jooq.tables.CharacterTempAbilityScore;
import net.nyrheim.penandpaper.database.jooq.tables.Player;
import net.nyrheim.penandpaper.database.jooq.tables.records.ActiveCharacterRecord;
import net.nyrheim.penandpaper.database.jooq.tables.records.CharacterAbilityScoreChoiceRecord;
import net.nyrheim.penandpaper.database.jooq.tables.records.CharacterAbilityScoreRecord;
import net.nyrheim.penandpaper.database.jooq.tables.records.CharacterClassRecord;
import net.nyrheim.penandpaper.database.jooq.tables.records.CharacterRecord;
import net.nyrheim.penandpaper.database.jooq.tables.records.CharacterTempAbilityScoreRecord;
import net.nyrheim.penandpaper.database.jooq.tables.records.PlayerRecord;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>nyrheim</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CharacterRecord, Integer> IDENTITY_CHARACTER = Identities0.IDENTITY_CHARACTER;
    public static final Identity<PlayerRecord, Integer> IDENTITY_PLAYER = Identities0.IDENTITY_PLAYER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ActiveCharacterRecord> KEY_ACTIVE_CHARACTER_PRIMARY = UniqueKeys0.KEY_ACTIVE_CHARACTER_PRIMARY;
    public static final UniqueKey<CharacterRecord> KEY_CHARACTER_PRIMARY = UniqueKeys0.KEY_CHARACTER_PRIMARY;
    public static final UniqueKey<CharacterAbilityScoreRecord> KEY_CHARACTER_ABILITY_SCORE_PRIMARY = UniqueKeys0.KEY_CHARACTER_ABILITY_SCORE_PRIMARY;
    public static final UniqueKey<CharacterAbilityScoreChoiceRecord> KEY_CHARACTER_ABILITY_SCORE_CHOICE_PRIMARY = UniqueKeys0.KEY_CHARACTER_ABILITY_SCORE_CHOICE_PRIMARY;
    public static final UniqueKey<CharacterClassRecord> KEY_CHARACTER_CLASS_PRIMARY = UniqueKeys0.KEY_CHARACTER_CLASS_PRIMARY;
    public static final UniqueKey<CharacterTempAbilityScoreRecord> KEY_CHARACTER_TEMP_ABILITY_SCORE_PRIMARY = UniqueKeys0.KEY_CHARACTER_TEMP_ABILITY_SCORE_PRIMARY;
    public static final UniqueKey<PlayerRecord> KEY_PLAYER_PRIMARY = UniqueKeys0.KEY_PLAYER_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ActiveCharacterRecord, CharacterRecord> ACTIVE_CHARACTER_CHARACTER_ID_FK = ForeignKeys0.ACTIVE_CHARACTER_CHARACTER_ID_FK;
    public static final ForeignKey<CharacterAbilityScoreRecord, CharacterRecord> CHARACTER_ABILITY_SCORE_CHARACTER_ID_FK = ForeignKeys0.CHARACTER_ABILITY_SCORE_CHARACTER_ID_FK;
    public static final ForeignKey<CharacterAbilityScoreChoiceRecord, CharacterRecord> CHARACTER_ABILITY_SCORE_CHOICE_CHARACTER_ID_FK = ForeignKeys0.CHARACTER_ABILITY_SCORE_CHOICE_CHARACTER_ID_FK;
    public static final ForeignKey<CharacterClassRecord, CharacterRecord> CHARACTER_CLASS_CHARACTER_ID_FK = ForeignKeys0.CHARACTER_CLASS_CHARACTER_ID_FK;
    public static final ForeignKey<CharacterTempAbilityScoreRecord, CharacterRecord> CHARACTER_TEMP_ABILITY_SCORE_CHARACTER_ID_FK = ForeignKeys0.CHARACTER_TEMP_ABILITY_SCORE_CHARACTER_ID_FK;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CharacterRecord, Integer> IDENTITY_CHARACTER = Internal.createIdentity(Character.CHARACTER, Character.CHARACTER.ID);
        public static Identity<PlayerRecord, Integer> IDENTITY_PLAYER = Internal.createIdentity(Player.PLAYER, Player.PLAYER.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ActiveCharacterRecord> KEY_ACTIVE_CHARACTER_PRIMARY = Internal.createUniqueKey(ActiveCharacter.ACTIVE_CHARACTER, "KEY_active_character_PRIMARY", new TableField[] { ActiveCharacter.ACTIVE_CHARACTER.PLAYER_ID }, true);
        public static final UniqueKey<CharacterRecord> KEY_CHARACTER_PRIMARY = Internal.createUniqueKey(Character.CHARACTER, "KEY_character_PRIMARY", new TableField[] { Character.CHARACTER.ID }, true);
        public static final UniqueKey<CharacterAbilityScoreRecord> KEY_CHARACTER_ABILITY_SCORE_PRIMARY = Internal.createUniqueKey(CharacterAbilityScore.CHARACTER_ABILITY_SCORE, "KEY_character_ability_score_PRIMARY", new TableField[] { CharacterAbilityScore.CHARACTER_ABILITY_SCORE.CHARACTER_ID, CharacterAbilityScore.CHARACTER_ABILITY_SCORE.ABILITY }, true);
        public static final UniqueKey<CharacterAbilityScoreChoiceRecord> KEY_CHARACTER_ABILITY_SCORE_CHOICE_PRIMARY = Internal.createUniqueKey(CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE, "KEY_character_ability_score_choice_PRIMARY", new TableField[] { CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID, CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY }, true);
        public static final UniqueKey<CharacterClassRecord> KEY_CHARACTER_CLASS_PRIMARY = Internal.createUniqueKey(CharacterClass.CHARACTER_CLASS, "KEY_character_class_PRIMARY", new TableField[] { CharacterClass.CHARACTER_CLASS.CHARACTER_ID, CharacterClass.CHARACTER_CLASS.CLASS_NAME }, true);
        public static final UniqueKey<CharacterTempAbilityScoreRecord> KEY_CHARACTER_TEMP_ABILITY_SCORE_PRIMARY = Internal.createUniqueKey(CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE, "KEY_character_temp_ability_score_PRIMARY", new TableField[] { CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID, CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE.ABILITY }, true);
        public static final UniqueKey<PlayerRecord> KEY_PLAYER_PRIMARY = Internal.createUniqueKey(Player.PLAYER, "KEY_player_PRIMARY", new TableField[] { Player.PLAYER.ID }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<ActiveCharacterRecord, CharacterRecord> ACTIVE_CHARACTER_CHARACTER_ID_FK = Internal.createForeignKey(Keys.KEY_CHARACTER_PRIMARY, ActiveCharacter.ACTIVE_CHARACTER, "active_character_character_id_fk", new TableField[] { ActiveCharacter.ACTIVE_CHARACTER.CHARACTER_ID }, true);
        public static final ForeignKey<CharacterAbilityScoreRecord, CharacterRecord> CHARACTER_ABILITY_SCORE_CHARACTER_ID_FK = Internal.createForeignKey(Keys.KEY_CHARACTER_PRIMARY, CharacterAbilityScore.CHARACTER_ABILITY_SCORE, "character_ability_score_character_id_fk", new TableField[] { CharacterAbilityScore.CHARACTER_ABILITY_SCORE.CHARACTER_ID }, true);
        public static final ForeignKey<CharacterAbilityScoreChoiceRecord, CharacterRecord> CHARACTER_ABILITY_SCORE_CHOICE_CHARACTER_ID_FK = Internal.createForeignKey(Keys.KEY_CHARACTER_PRIMARY, CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE, "character_ability_score_choice_character_id_fk", new TableField[] { CharacterAbilityScoreChoice.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID }, true);
        public static final ForeignKey<CharacterClassRecord, CharacterRecord> CHARACTER_CLASS_CHARACTER_ID_FK = Internal.createForeignKey(Keys.KEY_CHARACTER_PRIMARY, CharacterClass.CHARACTER_CLASS, "character_class_character_id_fk", new TableField[] { CharacterClass.CHARACTER_CLASS.CHARACTER_ID }, true);
        public static final ForeignKey<CharacterTempAbilityScoreRecord, CharacterRecord> CHARACTER_TEMP_ABILITY_SCORE_CHARACTER_ID_FK = Internal.createForeignKey(Keys.KEY_CHARACTER_PRIMARY, CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE, "character_temp_ability_score_character_id_fk", new TableField[] { CharacterTempAbilityScore.CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID }, true);
    }
}