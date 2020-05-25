package com.github.liamvii.penandpaper.rpkit.profile;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.database.table.PlayerTable;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PlayerId;
import com.github.liamvii.penandpaper.player.PlayerUUID;
import com.rpkit.players.bukkit.profile.RPKProfile;
import com.rpkit.players.bukkit.profile.RPKProfileProvider;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

public final class PnPProfileProvider implements RPKProfileProvider {

    private final Pen plugin;

    public PnPProfileProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addProfile(@NotNull RPKProfile profile) {

    }

    @Nullable
    @Override
    public RPKProfile getActiveProfile(@NotNull HttpServletRequest request) {
        return null;
    }

    @Nullable
    @Override
    public RPKProfile getProfile(int id) {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerId(id));
        if (penPlayer == null) return null;
        return new PnPProfileWrapper(plugin, penPlayer);
    }

    @Nullable
    @Override
    public RPKProfile getProfile(@NotNull String name) {
        OfflinePlayer bukkitPlayer = plugin.getServer().getOfflinePlayer(name);
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(bukkitPlayer.getUniqueId()));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, bukkitPlayer);
            playerTable.insert(penPlayer);
        }
        return new PnPProfileWrapper(plugin, penPlayer);
    }

    @Override
    public void removeProfile(@NotNull RPKProfile profile) {

    }

    @Override
    public void setActiveProfile(@NotNull HttpServletRequest request, @Nullable RPKProfile profile) {

    }

    @Override
    public void updateProfile(@NotNull RPKProfile profile) {

    }
}
