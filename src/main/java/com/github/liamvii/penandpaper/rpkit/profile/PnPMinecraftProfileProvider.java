package com.github.liamvii.penandpaper.rpkit.profile;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.database.table.PlayerTable;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PlayerId;
import com.github.liamvii.penandpaper.player.PlayerUUID;
import com.rpkit.players.bukkit.profile.*;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PnPMinecraftProfileProvider implements RPKMinecraftProfileProvider {

    private final Pen plugin;

    public PnPMinecraftProfileProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addMinecraftProfile(@NotNull RPKMinecraftProfile minecraftProfile) {

    }

    @Override
    public void addMinecraftProfileLinkRequest(@NotNull RPKMinecraftProfileLinkRequest minecraftProfileLinkRequest) {

    }

    @Override
    public void addMinecraftProfileToken(@NotNull RPKMinecraftProfileToken minecraftProfileToken) {

    }

    @Nullable
    @Override
    public RPKMinecraftProfile getMinecraftProfile(int id) {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer pnpPlayer = playerTable.get(new PlayerId(id));
        if (pnpPlayer == null) return null;
        return new PnPMinecraftProfileWrapper(plugin, pnpPlayer);
    }

    @Nullable
    @Override
    public RPKMinecraftProfile getMinecraftProfile(@NotNull OfflinePlayer player) {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(player.getUniqueId()));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, player);
            playerTable.insert(penPlayer);
        }
        return new PnPMinecraftProfileWrapper(plugin, penPlayer);
    }

    @NotNull
    @Override
    public List<RPKMinecraftProfileLinkRequest> getMinecraftProfileLinkRequests(@NotNull RPKMinecraftProfile minecraftProfile) {
        return new ArrayList<>();
    }

    @Nullable
    @Override
    public RPKMinecraftProfileToken getMinecraftProfileToken(@NotNull RPKMinecraftProfile minecraftProfile) {
        return null;
    }

    @Nullable
    @Override
    public RPKMinecraftProfileToken getMinecraftProfileToken(int id) {
        return null;
    }

    @NotNull
    @Override
    public List<RPKMinecraftProfile> getMinecraftProfiles(@NotNull RPKProfile profile) {
        if (profile instanceof PnPProfileWrapper) {
            PnPProfileWrapper profileWrapper = (PnPProfileWrapper) profile;
            PenPlayer pnpPlayer = profileWrapper.getPnPPlayer();
            return Collections.singletonList(
                    new PnPMinecraftProfileWrapper(plugin, pnpPlayer)
            );
        }
        return new ArrayList<>();
    }

    @Override
    public void removeMinecraftProfile(@NotNull RPKMinecraftProfile minecraftProfile) {

    }

    @Override
    public void removeMinecraftProfileLinkRequest(@NotNull RPKMinecraftProfileLinkRequest minecraftProfileLinkRequest) {

    }

    @Override
    public void removeMinecraftProfileToken(@NotNull RPKMinecraftProfileToken minecraftProfileToken) {

    }

    @Override
    public void updateMinecraftProfile(@NotNull RPKMinecraftProfile minecraftProfile) {

    }

    @Override
    public void updateMinecraftProfileLinkRequest(@NotNull RPKMinecraftProfileLinkRequest minecraftProfileLinkRequest) {

    }

    @Override
    public void updateMinecraftProfileToken(@NotNull RPKMinecraftProfileToken minecraftProfileToken) {

    }

}
