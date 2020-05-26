package com.github.liamvii.penandpaper.player;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.database.table.PlayerTable;
import org.bukkit.OfflinePlayer;

public final class PenPlayerService {

    private final Pen plugin;

    public PenPlayerService(Pen plugin) {
        this.plugin = plugin;
    }

    public PenPlayer getPlayer(PlayerUUID playerUUID) {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(playerUUID);
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, playerUUID);
            playerTable.insert(penPlayer);
        }
        return penPlayer;
    }

    public PenPlayer getPlayer(PlayerId playerId) {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        return playerTable.get(playerId);
    }

    public PenPlayer getPlayer(OfflinePlayer bukkitPlayer) {
        return getPlayer(new PlayerUUID(bukkitPlayer.getUniqueId()));
    }

}
