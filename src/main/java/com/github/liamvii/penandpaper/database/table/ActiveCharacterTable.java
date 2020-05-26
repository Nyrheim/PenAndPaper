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
    private final Cache<Integer, CharacterId> playerIdCache;
    private final Cache<Integer, PlayerId> characterIdCache;

    public ActiveCharacterTable(Database database) {
        this.database = database;
        this.playerIdCache = database.getCacheManager().createCache("penandpaper.active_character.player_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, CharacterId.class,
                        ResourcePoolsBuilder.heap(25)));
        this.characterIdCache = database.getCacheManager().createCache("penandpaper.active_character.character_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, PlayerId.class,
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
        if (playerIdCache.containsKey(playerId.getValue())) {
            return playerIdCache.get(playerId.getValue());
        }
        Record result = database.create()
                .select(ACTIVE_CHARACTER.CHARACTER_ID)
                .from(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .fetchOne();
        if (result == null) return null;
        CharacterId characterId = new CharacterId(result.get(ACTIVE_CHARACTER.CHARACTER_ID));
        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
        return characterId;
    }

    public PlayerId get(CharacterId characterId) {
        if (characterIdCache.containsKey(characterId.getValue())) {
            return characterIdCache.get(characterId.getValue());
        }
        Record result = database.create()
                .select(ACTIVE_CHARACTER.PLAYER_ID)
                .from(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.CHARACTER_ID.eq(characterId.getValue()))
                .fetchOne();
        if (result == null) return null;
        PlayerId playerId = new PlayerId(result.get(ACTIVE_CHARACTER.PLAYER_ID));
        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
        return playerId;
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

        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
    }

    public void update(PlayerId playerId, CharacterId characterId) {
        database.create()
                .update(ACTIVE_CHARACTER)
                .set(ACTIVE_CHARACTER.CHARACTER_ID, characterId.getValue())
                .where(ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .execute();

        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
    }

    public void delete(PlayerId playerId) {
        database.create()
                .deleteFrom(ACTIVE_CHARACTER)
                .where(ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .execute();

        playerIdCache.remove(playerId.getValue());
        characterIdCache.remove(playerId.getValue());
    }

    public void insertOrUpdate(PlayerId playerId, CharacterId characterId) {
        if (get(playerId) == null) {
            insert(playerId, characterId);
        } else {
            update(playerId, characterId);
        }
    }

}
