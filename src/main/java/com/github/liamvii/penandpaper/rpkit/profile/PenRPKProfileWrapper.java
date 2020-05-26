package com.github.liamvii.penandpaper.rpkit.profile;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.rpkit.players.bukkit.profile.RPKProfile;
import org.jetbrains.annotations.NotNull;

public final class PenRPKProfileWrapper implements RPKProfile {

    private final Pen plugin;
    private final PenPlayer pnpPlayer;

    public PenRPKProfileWrapper(Pen plugin, PenPlayer pnpPlayer) {
        this.plugin = plugin;
        this.pnpPlayer = pnpPlayer;
    }

    public PenPlayer getPnPPlayer() {
        return pnpPlayer;
    }

    @NotNull
    @Override
    public String getName() {
        return pnpPlayer.getPlayer().getName();
    }

    @Override
    public void setName(@NotNull String name) {

    }

    @NotNull
    @Override
    public byte[] getPasswordHash() {
        return new byte[0];
    }

    @Override
    public void setPasswordHash(byte @NotNull [] passwordHash) {

    }

    @NotNull
    @Override
    public byte[] getPasswordSalt() {
        return new byte[0];
    }

    @Override
    public void setPasswordSalt(byte @NotNull [] passwordSalt) {

    }

    @Override
    public boolean checkPassword(char @NotNull [] password) {
        return false;
    }

    @Override
    public void setPassword(char @NotNull [] password) {

    }

    @Override
    public int getId() {
        return pnpPlayer.getPlayerId().getValue();
    }

    @Override
    public void setId(int id) {

    }
}
