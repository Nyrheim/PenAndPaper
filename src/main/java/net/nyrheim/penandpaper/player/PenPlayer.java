package net.nyrheim.penandpaper.player;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.OfflinePlayer;

public final class PenPlayer {

    private final PenAndPaper plugin;
    private PlayerId playerId;
    private final PlayerUUID playerUUID;

    public PenPlayer(PenAndPaper plugin, PlayerId playerId, PlayerUUID playerUUID) {
        this.plugin = plugin;
        this.playerId = playerId;
        this.playerUUID = playerUUID;
    }

    public PenPlayer(PenAndPaper plugin, PlayerId playerId, OfflinePlayer player) {
        this(plugin, playerId, new PlayerUUID(player.getUniqueId()));
    }

    public PenPlayer(PenAndPaper plugin, OfflinePlayer player) {
        this(plugin, new PlayerId(0), player);
    }

    public PenPlayer(PenAndPaper plugin, PlayerUUID playerUUID) {
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
