package com.github.liamvii.penandpaper.database.table;

import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.database.Database;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;

import static com.github.liamvii.penandpaper.database.jooq.Tables.ACTIVE_CHARACTER;
import static com.github.liamvii.penandpaper.database.jooq.Tables.CHARACTER;
import static org.jooq.impl.DSL.constraint;

public final class ActiveCharacterTable implements Table {

    private final Database database;
    private final Cache<String, CharacterId> cache;

    public ActiveCharacterTable(Database database) {
        this.database = database;
        this.cache = database.getCacheManager().createCache("penandpaper.active_character.player_uuid",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, CharacterId.class,
                        ResourcePoolsBuilder.heap(25)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(ACTIVE_CHARACTER)
                .column(ACTIVE_CHARACTER.PLAYER_UUID)
                .column(ACTIVE_CHARACTER.CHARACTER_ID)
                .constraints(
                        constraint("active_character_pk").primaryKey(ACTIVE_CHARACTER.PLAYER_UUID),
                        constraint("active_character_character_id_fk")
                                .foreignKey(ACTIVE_CHARACTER.CHARACTER_ID)
                                .references(CHARACTER, CHARACTER.ID)
                )
                .execute();
    }

    public CharacterId get(PlayerId playerId) {
        if (cache.containsKey(playerId.getValue().toString())) {
            return cache.get(playerId.getValue().toString());
        }
        Record result = database.create()
                .select(ACTIVE_CHARACTER.CHARACTER_ID)
                .from(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.PLAYER_UUID.eq(playerId.getValue().toString()))
                .fetchOne();
        if (result == null) return null;
        CharacterId characterId = new CharacterId(result.get(ACTIVE_CHARACTER.CHARACTER_ID));
        cache.put(playerId.getValue().toString(), characterId);
        return characterId;
    }

    public void insert(PlayerId playerId, CharacterId characterId) {
        database.create()
                .insertInto(
                        ACTIVE_CHARACTER,
                        ACTIVE_CHARACTER.PLAYER_UUID,
                        ACTIVE_CHARACTER.CHARACTER_ID
                )
                .values(
                        playerId.getValue().toString(),
                        characterId.getValue()
                )
                .execute();

        cache.put(playerId.getValue().toString(), characterId);
    }

    public void update(PlayerId playerId, CharacterId characterId) {
        database.create()
                .update(ACTIVE_CHARACTER)
                .set(ACTIVE_CHARACTER.CHARACTER_ID, characterId.getValue())
                .where(ACTIVE_CHARACTER.PLAYER_UUID.eq(playerId.getValue().toString()))
                .execute();

        cache.put(playerId.getValue().toString(), characterId);
    }

    public void delete(PlayerId playerId) {
        database.create()
                .deleteFrom(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.PLAYER_UUID.eq(playerId.getValue().toString()))
                .execute();

        cache.remove(playerId.getValue().toString());
    }

    public void insertOrUpdate(PlayerId playerId, CharacterId characterId) {
        if (get(playerId) == null) {
            insert(playerId, characterId);
        } else {
            update(playerId, characterId);
        }
    }

}
