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
    private final Cache<Integer, CharacterId> cache;

    public ActiveCharacterTable(Database database) {
        this.database = database;
        this.cache = database.getCacheManager().createCache("penandpaper.active_character.player_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, CharacterId.class,
                        ResourcePoolsBuilder.heap(25)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(ACTIVE_CHARACTER)
                .column(ACTIVE_CHARACTER.PLAYER_ID)
                .column(ACTIVE_CHARACTER.CHARACTER_ID)
                .constraints(
                        constraint("active_character_pk").primaryKey(ACTIVE_CHARACTER.PLAYER_ID),
                        constraint("active_character_character_id_fk")
                                .foreignKey(ACTIVE_CHARACTER.CHARACTER_ID)
                                .references(CHARACTER, CHARACTER.ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                )
                .execute();
    }

    public CharacterId get(PlayerId playerId) {
        if (cache.containsKey(playerId.getValue())) {
            return cache.get(playerId.getValue());
        }
        Record result = database.create()
                .select(ACTIVE_CHARACTER.CHARACTER_ID)
                .from(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .fetchOne();
        if (result == null) return null;
        CharacterId characterId = new CharacterId(result.get(ACTIVE_CHARACTER.CHARACTER_ID));
        cache.put(playerId.getValue(), characterId);
        return characterId;
    }

    public void insert(PlayerId playerId, CharacterId characterId) {
        database.create()
                .insertInto(
                        ACTIVE_CHARACTER,
                        ACTIVE_CHARACTER.PLAYER_ID,
                        ACTIVE_CHARACTER.CHARACTER_ID
                )
                .values(
                        playerId.getValue(),
                        characterId.getValue()
                )
                .execute();

        cache.put(playerId.getValue(), characterId);
    }

    public void update(PlayerId playerId, CharacterId characterId) {
        database.create()
                .update(ACTIVE_CHARACTER)
                .set(ACTIVE_CHARACTER.CHARACTER_ID, characterId.getValue())
                .where(ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .execute();

        cache.put(playerId.getValue(), characterId);
    }

    public void delete(PlayerId playerId) {
        database.create()
                .deleteFrom(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .execute();

        cache.remove(playerId.getValue());
    }

    public void insertOrUpdate(PlayerId playerId, CharacterId characterId) {
        if (get(playerId) == null) {
            insert(playerId, characterId);
        } else {
            update(playerId, characterId);
        }
    }

}
