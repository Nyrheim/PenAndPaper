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

import static net.nyrheim.penandpaper.database.jooq.Tables.CHARACTER_ABILITY_SCORE;
import static org.jooq.impl.DSL.constraint;

public final class CharacterAbilityScoreTable implements Table {

    private final Database database;

    private final Cache<Integer, Map> cache;

    public CharacterAbilityScoreTable(Database database) {
        this.database = database;
        this.cache = database.getCacheManager().createCache("penandpaper.character_ability_score.character_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Map.class,
                        ResourcePoolsBuilder.heap(60)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(CHARACTER_ABILITY_SCORE)
                .column(CHARACTER_ABILITY_SCORE.CHARACTER_ID)
                .column(CHARACTER_ABILITY_SCORE.ABILITY)
                .column(CHARACTER_ABILITY_SCORE.SCORE)
                .constraints(
                        constraint("character_ability_score_pk").primaryKey(
                                CHARACTER_ABILITY_SCORE.CHARACTER_ID,
                                CHARACTER_ABILITY_SCORE.ABILITY
                        ),
                        constraint("character_ability_score_character_id_fk")
                                .foreignKey(CHARACTER_ABILITY_SCORE.CHARACTER_ID)
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
                        CHARACTER_ABILITY_SCORE.ABILITY,
                        CHARACTER_ABILITY_SCORE.SCORE
                )
                .from(CHARACTER_ABILITY_SCORE)
                .where(CHARACTER_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        result -> Ability.getByAbbreviation(result.get(CHARACTER_ABILITY_SCORE.ABILITY)),
                        result -> result.get(CHARACTER_ABILITY_SCORE.SCORE)
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
                        CHARACTER_ABILITY_SCORE,
                        CHARACTER_ABILITY_SCORE.CHARACTER_ID,
                        CHARACTER_ABILITY_SCORE.ABILITY,
                        CHARACTER_ABILITY_SCORE.SCORE
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
                .update(CHARACTER_ABILITY_SCORE)
                .set(CHARACTER_ABILITY_SCORE.SCORE, score)
                .where(CHARACTER_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_ABILITY_SCORE.ABILITY.eq(ability.getAbbreviation()))
                .execute();
        Map<Ability, Integer> abilityScores = cache.get(characterId.getValue());
        if (abilityScores == null) abilityScores = new EnumMap<>(Ability.class);
        abilityScores.put(ability, score);
        cache.put(characterId.getValue(), abilityScores);
    }

    public void delete(CharacterId characterId, Ability ability) {
        database.create()
                .deleteFrom(CHARACTER_ABILITY_SCORE)
                .where(CHARACTER_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_ABILITY_SCORE.ABILITY.eq(ability.getAbbreviation()))
                .execute();
        Map<Ability, Integer> abilities = cache.get(characterId.getValue());
        abilities.remove(ability);
        cache.put(characterId.getValue(), abilities);
    }

    public void delete(CharacterId characterId) {
        database.create()
                .deleteFrom(CHARACTER_ABILITY_SCORE)
                .where(CHARACTER_ABILITY_SCORE.CHARACTER_ID.eq(characterId.getValue()))
                .execute();
        cache.remove(characterId.getValue());
    }

    public void insertOrUpdateAbilityScores(PenCharacter character) {
        for (Ability ability : Ability.values()) {
            if (get(character.getId(), ability) == null) {
                insert(character.getId(), ability, character.getAbilityScore(ability));
            } else {
                update(character.getId(), ability, character.getAbilityScore(ability));
            }
        }
    }
}
