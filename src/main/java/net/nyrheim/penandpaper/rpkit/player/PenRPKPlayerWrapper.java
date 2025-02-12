package net.nyrheim.penandpaper.rpkit.player;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.player.PenPlayer;
import com.rpkit.players.bukkit.player.RPKPlayer;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PenRPKPlayerWrapper implements RPKPlayer {

    private final PenAndPaper plugin;
    private final PenPlayer pnpPlayer;

    public PenRPKPlayerWrapper(PenAndPaper plugin, PenPlayer pnpPlayer) {
        this.plugin = plugin;
        this.pnpPlayer = pnpPlayer;
    }

    @Nullable
    @Override
    public OfflinePlayer getBukkitPlayer() {
        return plugin.getServer().getOfflinePlayer(pnpPlayer.getPlayerUUID().getValue());
    }

    @Override
    public void setBukkitPlayer(@Nullable OfflinePlayer bukkitPlayer) {

    }

    @Nullable
    @Override
    public String getIrcNick() {
        return null;
    }

    @Override
    public void setIrcNick(@Nullable String ircNick) {

    }

    @Nullable
    @Override
    public String getLastKnownIP() {
        return null;
    }

    @Override
    public void setLastKnownIP(@Nullable String lastKnownIP) {

    }

    @NotNull
    @Override
    public String getName() {
        return pnpPlayer.getPlayer().getName();
    }

    @Override
    public void setName(@NotNull String name) {

    }

    @Override
    public int getId() {
        return pnpPlayer.getPlayerId().getValue();
    }

    @Override
    public void setId(int id) {

    }
}
