package net.nyrheim.penandpaper.database.table;

import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.player.PlayerId;
import net.nyrheim.penandpaper.database.jooq.Tables;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;

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
                .createTableIfNotExists(Tables.ACTIVE_CHARACTER)
                .column(Tables.ACTIVE_CHARACTER.PLAYER_ID)
                .column(Tables.ACTIVE_CHARACTER.CHARACTER_ID)
                .constraints(
                        constraint("active_character_pk").primaryKey(Tables.ACTIVE_CHARACTER.PLAYER_ID),
                        constraint("active_character_character_id_fk")
                                .foreignKey(Tables.ACTIVE_CHARACTER.CHARACTER_ID)
                                .references(Tables.CHARACTER, Tables.CHARACTER.ID)
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
                .select(Tables.ACTIVE_CHARACTER.CHARACTER_ID)
                .from(Tables.ACTIVE_CHARACTER)
                .where(Tables.ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .fetchOne();
        if (result == null) return null;
        CharacterId characterId = new CharacterId(result.get(Tables.ACTIVE_CHARACTER.CHARACTER_ID));
        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
        return characterId;
    }

    public PlayerId get(CharacterId characterId) {
        if (characterIdCache.containsKey(characterId.getValue())) {
            return characterIdCache.get(characterId.getValue());
        }
        Record result = database.create()
                .select(Tables.ACTIVE_CHARACTER.PLAYER_ID)
                .from(Tables.ACTIVE_CHARACTER)
                .where(Tables.ACTIVE_CHARACTER.CHARACTER_ID.eq(characterId.getValue()))
                .fetchOne();
        if (result == null) return null;
        PlayerId playerId = new PlayerId(result.get(Tables.ACTIVE_CHARACTER.PLAYER_ID));
        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
        return playerId;
    }

    public void insert(PlayerId playerId, CharacterId characterId) {
        database.create()
                .insertInto(
                        Tables.ACTIVE_CHARACTER,
                        Tables.ACTIVE_CHARACTER.PLAYER_ID,
                        Tables.ACTIVE_CHARACTER.CHARACTER_ID
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
                .update(Tables.ACTIVE_CHARACTER)
                .set(Tables.ACTIVE_CHARACTER.CHARACTER_ID, characterId.getValue())
                .where(Tables.ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .execute();

        playerIdCache.put(playerId.getValue(), characterId);
        characterIdCache.put(characterId.getValue(), playerId);
    }

    public void delete(PlayerId playerId) {
        database.create()
                .deleteFrom(Tables.ACTIVE_CHARACTER)
                .where(Tables.ACTIVE_CHARACTER.PLAYER_ID.eq(playerId.getValue()))
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
