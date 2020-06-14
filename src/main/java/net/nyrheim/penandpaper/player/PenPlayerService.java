package net.nyrheim.penandpaper.player;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.database.jooq.tables.Player;
import net.nyrheim.penandpaper.database.table.PlayerTable;
import org.bukkit.OfflinePlayer;

public final class PenPlayerService {

    private final PenAndPaper plugin;

    public PenPlayerService(PenAndPaper plugin) {
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

    public void updatePlayer(PenPlayer player) {
        plugin.getDatabase().getTable(PlayerTable.class).update(player);
    }

}
