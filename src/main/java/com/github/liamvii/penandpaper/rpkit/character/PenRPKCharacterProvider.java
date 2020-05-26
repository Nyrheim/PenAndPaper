package com.github.liamvii.penandpaper.rpkit.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import com.github.liamvii.penandpaper.player.PlayerUUID;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.characters.bukkit.character.RPKCharacterProvider;
import com.rpkit.players.bukkit.player.RPKPlayer;
import com.rpkit.players.bukkit.profile.RPKMinecraftProfile;
import com.rpkit.players.bukkit.profile.RPKProfile;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class PenRPKCharacterProvider implements RPKCharacterProvider {

    private final Pen plugin;

    public PenRPKCharacterProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addCharacter(@NotNull RPKCharacter character) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.addCharacter(wrapper.getPnPCharacter());
        }
    }

    @Nullable
    @Override
    public RPKCharacter getActiveCharacter(@NotNull RPKPlayer player) {
        OfflinePlayer bukkitPlayer = player.getBukkitPlayer();
        if (bukkitPlayer == null) return null;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        return new PenRPKCharacterWrapper(plugin, character);
    }

    @Nullable
    @Override
    public RPKCharacter getActiveCharacter(@NotNull RPKMinecraftProfile minecraftProfile) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(new PlayerUUID(minecraftProfile.getMinecraftUUID()));
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) return null;
        return new PenRPKCharacterWrapper(plugin, character);
    }

    @Nullable
    @Override
    public RPKCharacter getCharacter(int id) {
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getCharacter(new CharacterId(id));
        if (character == null) return null;
        return new PenRPKCharacterWrapper(plugin, character);
    }

    @NotNull
    @Override
    public Collection<RPKCharacter> getCharacters(@NotNull RPKPlayer player) {
        OfflinePlayer bukkitPlayer = player.getBukkitPlayer();
        if (bukkitPlayer == null) return new ArrayList<>();
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        return characterService.getCharacters(penPlayer).stream()
                .map(character -> new PenRPKCharacterWrapper(plugin, character)).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public Collection<RPKCharacter> getCharacters(@NotNull RPKProfile profile) {
        OfflinePlayer bukkitPlayer = plugin.getServer().getPlayerExact(profile.getName());
        if (bukkitPlayer == null) return new ArrayList<>();
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        return characterService.getCharacters(penPlayer).stream()
                .map(character -> new PenRPKCharacterWrapper(plugin, character)).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<RPKCharacter> getCharacters(@NotNull String name) {
        return new ArrayList<>();
    }

    @Override
    public void removeCharacter(@NotNull RPKCharacter character) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.deleteCharacter(wrapper.getPnPCharacter());
        }
    }

    @Override
    public void setActiveCharacter(@NotNull RPKPlayer player, @Nullable RPKCharacter character) {
        OfflinePlayer bukkitPlayer = player.getBukkitPlayer();
        if (bukkitPlayer == null) return;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(bukkitPlayer);
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.setActiveCharacter(penPlayer, wrapper.getPnPCharacter());
        }
    }

    @Override
    public void setActiveCharacter(@NotNull RPKMinecraftProfile minecraftProfile, @Nullable RPKCharacter character) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(new PlayerUUID(minecraftProfile.getMinecraftUUID()));
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.setActiveCharacter(penPlayer, wrapper.getPnPCharacter());
        }
    }

    @Override
    public void updateCharacter(@NotNull RPKCharacter character) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.updateCharacter(wrapper.getPnPCharacter());
        }
    }

}
