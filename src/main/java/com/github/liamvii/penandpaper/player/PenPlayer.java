package com.github.liamvii.penandpaper.player;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.OfflinePlayer;

public final class PenPlayer {

    private final Pen plugin;
    private PlayerId playerId;
    private final PlayerUUID playerUUID;

    public PenPlayer(Pen plugin, PlayerId playerId, PlayerUUID playerUUID) {
        this.plugin = plugin;
        this.playerId = playerId;
        this.playerUUID = playerUUID;
    }

    public PenPlayer(Pen plugin, PlayerId playerId, OfflinePlayer player) {
        this(plugin, playerId, new PlayerUUID(player.getUniqueId()));
    }

    public PenPlayer(Pen plugin, OfflinePlayer player) {
        this(plugin, new PlayerId(0), player);
    }

    public PenPlayer(Pen plugin, PlayerUUID playerUUID) {
        this(plugin, new PlayerId(0), playerUUID);
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public PlayerUUID getPlayerUUID() {
        return playerUUID;
    }

    public OfflinePlayer getPlayer() {
        return plugin.getServer().getOfflinePlayer(getPlayerUUID().getValue());
    }

}
