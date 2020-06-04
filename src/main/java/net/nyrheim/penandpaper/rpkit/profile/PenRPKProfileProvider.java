package net.nyrheim.penandpaper.rpkit.profile;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.player.PlayerId;
import com.rpkit.players.bukkit.profile.RPKProfile;
import com.rpkit.players.bukkit.profile.RPKProfileProvider;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

public final class PenRPKProfileProvider implements RPKProfileProvider {

    private final PenAndPaper plugin;

    public PenRPKProfileProvider(PenAndPaper plugin) {
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
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(new PlayerId(id));
        if (penPlayer == null) return null;
        return new PenRPKProfileWrapper(plugin, penPlayer);
    }

    @Nullable
    @Override
    public RPKProfile getProfile(@NotNull String name) {
        OfflinePlayer bukkitPlayer = plugin.getServer().getOfflinePlayer(name);
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        return new PenRPKProfileWrapper(plugin, penPlayer);
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
