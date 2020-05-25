package com.github.liamvii.penandpaper.database.table;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.database.Database;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PlayerId;
import com.github.liamvii.penandpaper.player.PlayerUUID;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;

import java.util.UUID;

import static com.github.liamvii.penandpaper.database.jooq.Tables.PLAYER;
import static org.jooq.impl.DSL.constraint;

public final class PlayerTable implements Table {

    private final Pen plugin;

    private final Database database;

    private final Cache<Integer, PenPlayer> idCache;
    private final Cache<String, PenPlayer> uuidCache;

    public PlayerTable(Pen plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
        this.idCache = database.getCacheManager()
                .createCache("penandpaper.player.id",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, PenPlayer.class,
                                ResourcePoolsBuilder.heap(25L)));
        this.uuidCache = database.getCacheManager()
                .createCache("penandpaper.player.uuid",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, PenPlayer.class,
                                ResourcePoolsBuilder.heap(25L)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(PLAYER)
                .column(PLAYER.ID)
                .column(PLAYER.PLAYER_UUID)
                .constraints(
                        constraint("player_pk").primaryKey(PLAYER.ID)
                )
                .execute();
    }

    public void insert(PenPlayer player) {
        database.create()
                .insertInto(
                        PLAYER,
                        PLAYER.PLAYER_UUID
                )
                .values(
                        player.getPlayerUUID().getValue().toString()
                )
                .execute();
        int id = database.create().lastID().intValue();
        player.setPlayerId(new PlayerId(id));
        idCache.put(player.getPlayerId().getValue(), player);
        uuidCache.put(player.getPlayerUUID().getValue().toString(), player);
    }

    public PenPlayer get(PlayerId playerId) {
        if (idCache.containsKey(playerId.getValue())) {
            return idCache.get(playerId.getValue());
        }
        Record result = database.create()
                .select(PLAYER.PLAYER_UUID)
                .from(PLAYER)
                .where(PLAYER.ID.eq(playerId.getValue()))
                .fetchOne();
        if (result == null) return null;
        PenPlayer penPlayer = new PenPlayer(
                plugin,
                playerId,
                new PlayerUUID(UUID.fromString(result.get(PLAYER.PLAYER_UUID)))
        );
        idCache.put(playerId.getValue(), penPlayer);
        uuidCache.put(penPlayer.getPlayerUUID().getValue().toString(), penPlayer);
        return penPlayer;
    }

    public PenPlayer get(PlayerUUID playerUUID) {
        if (uuidCache.containsKey(playerUUID.getValue().toString())) {
            return uuidCache.get(playerUUID.getValue().toString());
        }
        Record result = database.create()
                .select(PLAYER.ID)
                .from(PLAYER)
                .where(PLAYER.PLAYER_UUID.eq(playerUUID.getValue().toString()))
                .fetchOne();
        if (result == null) return null;
        PenPlayer penPlayer = new PenPlayer(
                plugin,
                new PlayerId(result.get(PLAYER.ID)),
                playerUUID
        );
        idCache.put(penPlayer.getPlayerId().getValue(), penPlayer);
        uuidCache.put(playerUUID.getValue().toString(), penPlayer);
        return penPlayer;
    }

    public void delete(PenPlayer player) {
        database.create()
                .deleteFrom(PLAYER)
                .where(PLAYER.ID.eq(player.getPlayerId().getValue()))
                .execute();
        idCache.remove(player.getPlayerId().getValue());
        uuidCache.remove(player.getPlayerUUID().getValue().toString());
    }

}
