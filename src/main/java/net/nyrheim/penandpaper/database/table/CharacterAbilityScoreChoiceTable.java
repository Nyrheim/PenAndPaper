package net.nyrheim.penandpaper.database.table;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.database.jooq.Tables;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.constraint;

public final class CharacterAbilityScoreChoiceTable implements Table {

    private final Database database;

    private final Cache<Integer, Map> cache;

    public CharacterAbilityScoreChoiceTable(Database database) {
        this.database = database;
        this.cache = database.getCacheManager().createCache("penandpaper.character_ability_score_choice.character_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Map.class,
                        ResourcePoolsBuilder.heap(25)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(Tables.CHARACTER_ABILITY_SCORE_CHOICE)
                .column(Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID)
                .column(Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY)
                .column(Tables.CHARACTER_ABILITY_SCORE_CHOICE.SCORE)
                .constraints(
                        constraint("character_ability_score_choice_pk").primaryKey(
                                Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID,
                                Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY
                        ),
                        constraint("character_ability_score_choice_character_id_fk")
                                .foreignKey(Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID)
                                .references(Tables.CHARACTER, Tables.CHARACTER.ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                )
                .execute();
    }

    public Map<Ability, Integer> get(CharacterId characterId) {
        if (cache.containsKey(characterId.getValue())) {
            return cache.get(characterId.getValue());
        }
        Map<Ability, Integer> abilities = database.create()
                .select(
                        Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY,
                        Tables.CHARACTER_ABILITY_SCORE_CHOICE.SCORE
                )
                .from(Tables.CHARACTER_ABILITY_SCORE_CHOICE)
                .where(Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID.eq(characterId.getValue()))
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        result -> Ability.getByAbbreviation(result.get(Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY)),
                        result -> result.get(Tables.CHARACTER_ABILITY_SCORE_CHOICE.SCORE)
                ));
        cache.put(characterId.getValue(), abilities);
        return abilities;
    }
    
    public Integer get(CharacterId characterId, Ability ability) {
        return get(characterId).get(ability);
    }

    public void insert(CharacterId characterId, Ability ability, int score) {
        database.create()
                .insertInto(
                        Tables.CHARACTER_ABILITY_SCORE_CHOICE,
                        Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID,
                        Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY,
                        Tables.CHARACTER_ABILITY_SCORE_CHOICE.SCORE
                )
                .values(
                        characterId.getValue(),
                        ability.getAbbreviation(),
                        score
                )
                .execute();
        Map<Ability, Integer> abilityScores = cache.get(characterId.getValue());
        if (abilityScores == null) abilityScores = new EnumMap<>(Ability.class);
        abilityScores.put(ability, score);
        cache.put(characterId.getValue(), abilityScores);
    }

    public void update(CharacterId characterId, Ability ability, int score) {
        database.create()
                .update(Tables.CHARACTER_ABILITY_SCORE_CHOICE)
                .set(Tables.CHARACTER_ABILITY_SCORE_CHOICE.SCORE, score)
                .where(Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID.eq(characterId.getValue()))
                .and(Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY.eq(ability.getAbbreviation()))
                .execute();
        Map<Ability, Integer> abilityScores = cache.get(characterId.getValue());
        if (abilityScores == null) abilityScores = new EnumMap<>(Ability.class);
        abilityScores.put(ability, score);
        cache.put(characterId.getValue(), abilityScores);
    }

    public void delete(CharacterId characterId, Ability ability) {
        database.create()
                .deleteFrom(Tables.CHARACTER_ABILITY_SCORE_CHOICE)
                .where(Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID.eq(characterId.getValue()))
                .and(Tables.CHARACTER_ABILITY_SCORE_CHOICE.ABILITY.eq(ability.getAbbreviation()))
                .execute();
        Map<Ability, Integer> abilities = cache.get(characterId.getValue());
        abilities.remove(ability);
        cache.put(characterId.getValue(), abilities);
    }

    public void delete(CharacterId characterId) {
        database.create()
                .deleteFrom(Tables.CHARACTER_ABILITY_SCORE_CHOICE)
                .where(Tables.CHARACTER_ABILITY_SCORE_CHOICE.CHARACTER_ID.eq(characterId.getValue()))
                .execute();
        cache.remove(characterId.getValue());
    }

    public void insertOrUpdateAbilityScores(PenCharacter character) {
        for (Ability ability : Ability.values()) {
            if (get(character.getId(), ability) == null) {
                insert(character.getId(), ability, character.getAbilityScoreChoice(ability));
            } else {
                update(character.getId(), ability, character.getAbilityScoreChoice(ability));
            }
        }
    }
}
