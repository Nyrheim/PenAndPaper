package com.github.liamvii.penandpaper.rpkit.profile;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.rpkit.players.bukkit.profile.RPKMinecraftProfile;
import com.rpkit.players.bukkit.profile.RPKProfile;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class PnPMinecraftProfileWrapper implements RPKMinecraftProfile {

    private final Pen plugin;
    private final PenPlayer pnpPlayer;

    public PnPMinecraftProfileWrapper(Pen plugin, PenPlayer pnpPlayer) {
        this.plugin = plugin;
        this.pnpPlayer = pnpPlayer;
    }

    public PenPlayer getPnPPlayer() {
        return pnpPlayer;
    }

    @Override
    public boolean isOnline() {
        return pnpPlayer.getPlayer().isOnline();
    }

    @NotNull
    @Override
    public UUID getMinecraftUUID() {
        return pnpPlayer.getPlayerUUID().getValue();
    }

    @NotNull
    @Override
    public String getMinecraftUsername() {
        return pnpPlayer.getPlayer().getName();
    }

    @Nullable
    @Override
    public RPKProfile getProfile() {
        return new PnPProfileWrapper(plugin, pnpPlayer);
    }

    @Override
    public void setProfile(@Nullable RPKProfile profile) {

    }

    @Override
    public void sendMessage(BaseComponent @NotNull ... baseComponents) {
        OfflinePlayer bukkitOfflinePlayer = pnpPlayer.getPlayer();
        if (bukkitOfflinePlayer.isOnline()) {
            Player bukkitPlayer = bukkitOfflinePlayer.getPlayer();
            if (bukkitPlayer != null) {
                bukkitPlayer.spigot().sendMessage(baseComponents);
            }
        }
    }

    @Override
    public void sendMessage(@NotNull String message) {
        OfflinePlayer bukkitOfflinePlayer = pnpPlayer.getPlayer();
        if (bukkitOfflinePlayer.isOnline()) {
            Player bukkitPlayer = bukkitOfflinePlayer.getPlayer();
            if (bukkitPlayer != null) {
                bukkitPlayer.sendMessage(message);
            }
        }
    }

    @Override
    public int getId() {
        return pnpPlayer.getPlayerId().getValue();
    }

    @Override
    public void setId(int id) {

    }
}
