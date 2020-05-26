package com.github.liamvii.penandpaper.rpkit.player;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import com.rpkit.players.bukkit.player.RPKPlayer;
import com.rpkit.players.bukkit.player.RPKPlayerProvider;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.pircbotx.User;

import java.net.InetAddress;

public final class PenRPKPlayerProvider implements RPKPlayerProvider {

    private final Pen plugin;

    public PenRPKPlayerProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addPlayer(@NotNull RPKPlayer rpkPlayer) {

    }

    @Nullable
    @Override
    public RPKPlayer getPlayer(@NotNull InetAddress inetAddress) {
        return null;
    }

    @Nullable
    @Override
    public RPKPlayer getPlayer(int id) {
        return null;
    }

    @Nullable
    @Override
    public RPKPlayer getPlayer(@NotNull String name) {
        OfflinePlayer bukkitPlayer = plugin.getServer().getOfflinePlayer(name);
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        return new PenRPKPlayerWrapper(plugin, penPlayer);
    }

    @NotNull
    @Override
    public RPKPlayer getPlayer(@NotNull OfflinePlayer bukkitPlayer) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        return new PenRPKPlayerWrapper(plugin, penPlayer);
    }

    @NotNull
    @Override
    public RPKPlayer getPlayer(@NotNull User user) {
        return null;
    }

    @Override
    public void removePlayer(@NotNull RPKPlayer player) {

    }

    @Override
    public void updatePlayer(@NotNull RPKPlayer player) {

    }
}
