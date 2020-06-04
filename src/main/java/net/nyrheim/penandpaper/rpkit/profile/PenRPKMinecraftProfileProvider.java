package net.nyrheim.penandpaper.rpkit.profile;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.player.PlayerId;
import com.rpkit.players.bukkit.profile.*;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PenRPKMinecraftProfileProvider implements RPKMinecraftProfileProvider {

    private final PenAndPaper plugin;

    public PenRPKMinecraftProfileProvider(PenAndPaper plugin) {
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
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(new PlayerId(id));
        if (penPlayer == null) return null;
        return new PenRPKMinecraftProfileWrapper(plugin, penPlayer);
    }

    @Nullable
    @Override
    public RPKMinecraftProfile getMinecraftProfile(@NotNull OfflinePlayer player) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        return new PenRPKMinecraftProfileWrapper(plugin, penPlayer);
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
        if (profile instanceof PenRPKProfileWrapper) {
            PenRPKProfileWrapper profileWrapper = (PenRPKProfileWrapper) profile;
            PenPlayer pnpPlayer = profileWrapper.getPnPPlayer();
            return Collections.singletonList(
                    new PenRPKMinecraftProfileWrapper(plugin, pnpPlayer)
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
