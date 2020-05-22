package com.github.liamvii.penandpaper.database.table;

import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.Database;
import org.jooq.Record;

import java.util.Map;
import java.util.stream.Collectors;

import static com.github.liamvii.penandpaper.database.jooq.nyrheim.Tables.CHARACTER_ABILITY_SCORE;
import static com.github.liamvii.penandpaper.database.jooq.nyrheim.Tables.CHARACTER_TEMP_ABILITY_SCORE;
import static org.jooq.impl.DSL.constraint;

public class CharacterTempAbilityScoreTable implements Table {

    private final Database database;

    public CharacterTempAbilityScoreTable(Database database) {
        this.database = database;
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(CHARACTER_TEMP_ABILITY_SCORE)
                .column(CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID)
                .column(CHARACTER_TEMP_ABILITY_SCORE.ABILITY)
                .column(CHARACTER_TEMP_ABILITY_SCORE.SCORE)
                .constraints(
                        constraint("character_temp_ability_score_pk").primaryKey(
                                CHARACTER_ABILITY_SCORE.CHARACTER_ID,
                                CHARACTER_ABILITY_SCORE.ABILITY
                        )
                )
                .execute();
    }

    public Map<Ability, Integer> get(CharacterId characterId) {
        return database.create()
                .select(
                        CHARACTER_TEMP_ABILITY_SCORE.ABILITY,
                        CHARACTER_TEMP_ABILITY_SCORE.SCORE
                )
                .from(CHARACTER_TEMP_ABILITY_SCORE)
                .where(CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        result -> Ability.valueOf(result.get(CHARACTER_TEMP_ABILITY_SCORE.ABILITY)),
                        result -> result.get(CHARACTER_TEMP_ABILITY_SCORE.SCORE)
                ));
    }

    public Integer get(CharacterId characterId, Ability ability) {
        Record result = database.create()
                .select(CHARACTER_TEMP_ABILITY_SCORE.SCORE)
                .from(CHARACTER_TEMP_ABILITY_SCORE)
                .where(CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_TEMP_ABILITY_SCORE.ABILITY.eq(ability.getAbbreviation()))
                .fetchOne();
        if (result == null) return null;
        return result.get(CHARACTER_ABILITY_SCORE.SCORE);
    }

    public void insert(CharacterId characterId, Ability ability, int score) {
        database.create()
                .insertInto(
                        CHARACTER_TEMP_ABILITY_SCORE,
                        CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID,
                        CHARACTER_TEMP_ABILITY_SCORE.ABILITY,
                        CHARACTER_TEMP_ABILITY_SCORE.SCORE
                )
                .values(
                        characterId.getValue(),
                        ability.name(),
                        score
                )
                .execute();
    }

    public void update(CharacterId characterId, Ability ability, int score) {
        database.create()
                .update(CHARACTER_TEMP_ABILITY_SCORE)
                .set(CHARACTER_TEMP_ABILITY_SCORE.SCORE, score)
                .where(CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_TEMP_ABILITY_SCORE.ABILITY.eq(ability.name()))
                .execute();
    }

    public void delete(CharacterId characterId, Ability ability) {
        database.create()
                .deleteFrom(CHARACTER_TEMP_ABILITY_SCORE)
                .where(CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_TEMP_ABILITY_SCORE.ABILITY.eq(ability.name()))
                .execute();
    }

    public void delete(CharacterId characterId) {
        database.create()
                .deleteFrom(CHARACTER_TEMP_ABILITY_SCORE)
                .where(CHARACTER_TEMP_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .execute();
    }

    public void insertOrUpdateAbilityScores(PlayerCharacter character) {
        for (Ability ability : Ability.values()) {
            if (get(character.getId(), ability) == null) {
                insert(character.getId(), ability, character.getTempScore(ability));
            } else {
                update(character.getId(), ability, character.getTempScore(ability));
            }
        }
    }

}
