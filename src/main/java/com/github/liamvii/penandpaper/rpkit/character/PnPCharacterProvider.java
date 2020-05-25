package com.github.liamvii.penandpaper.rpkit.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.database.table.PlayerTable;
import com.github.liamvii.penandpaper.player.PenPlayer;
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

public final class PnPCharacterProvider implements RPKCharacterProvider {

    private final Pen plugin;

    public PnPCharacterProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addCharacter(@NotNull RPKCharacter character) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            plugin.getDatabase().getTable(CharacterTable.class).insert(wrapper.getPnPCharacter());
        }
    }

    @Nullable
    @Override
    public RPKCharacter getActiveCharacter(@NotNull RPKPlayer player) {
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        OfflinePlayer bukkitPlayer = player.getBukkitPlayer();
        if (bukkitPlayer == null) return null;
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(bukkitPlayer));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, bukkitPlayer);
            playerTable.insert(penPlayer);
        }
        CharacterId activeCharacterId = activeCharacterTable.get(penPlayer.getPlayerId());
        if (activeCharacterId == null) return null;
        PlayerCharacter character = characterTable.get(activeCharacterId);
        return new PnPCharacterWrapper(plugin, character);
    }

    @Nullable
    @Override
    public RPKCharacter getActiveCharacter(@NotNull RPKMinecraftProfile minecraftProfile) {
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(minecraftProfile.getMinecraftUUID()));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, new PlayerUUID(minecraftProfile.getMinecraftUUID()));
            playerTable.insert(penPlayer);
        }
        CharacterId activeCharacterId = activeCharacterTable.get(penPlayer.getPlayerId());
        if (activeCharacterId == null) return null;
        PlayerCharacter character = characterTable.get(activeCharacterId);
        return new PnPCharacterWrapper(plugin, character);
    }

    @Nullable
    @Override
    public RPKCharacter getCharacter(int id) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        PlayerCharacter character = characterTable.get(new CharacterId(id));
        if (character == null) return null;
        return new PnPCharacterWrapper(plugin, character);
    }

    @NotNull
    @Override
    public Collection<RPKCharacter> getCharacters(@NotNull RPKPlayer player) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        OfflinePlayer bukkitPlayer = player.getBukkitPlayer();
        if (bukkitPlayer == null) return new ArrayList<>();
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(player.getBukkitPlayer()));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, player.getBukkitPlayer());
            playerTable.insert(penPlayer);
        }
        return characterTable.get(penPlayer.getPlayerId()).stream()
                .map(character -> new PnPCharacterWrapper(plugin, character)).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public Collection<RPKCharacter> getCharacters(@NotNull RPKProfile profile) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        OfflinePlayer bukkitPlayer = plugin.getServer().getPlayerExact(profile.getName());
        if (bukkitPlayer == null) return new ArrayList<>();
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(bukkitPlayer));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, bukkitPlayer);
            playerTable.insert(penPlayer);
        }
        return characterTable.get(penPlayer.getPlayerId()).stream()
                .map(character -> new PnPCharacterWrapper(plugin, character)).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<RPKCharacter> getCharacters(@NotNull String name) {
        return new ArrayList<>();
    }

    @Override
    public void removeCharacter(@NotNull RPKCharacter character) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            plugin.getDatabase().getTable(CharacterTable.class).delete(wrapper.getPnPCharacter());
        }
    }

    @Override
    public void setActiveCharacter(@NotNull RPKPlayer player, @Nullable RPKCharacter character) {
        OfflinePlayer bukkitPlayer = player.getBukkitPlayer();
        if (bukkitPlayer == null) return;
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(bukkitPlayer));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, bukkitPlayer);
            playerTable.insert(penPlayer);
        }
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            penPlayer.switchCharacter(wrapper.getPnPCharacter());
        }
    }

    @Override
    public void setActiveCharacter(@NotNull RPKMinecraftProfile minecraftProfile, @Nullable RPKCharacter character) {
        PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
        PenPlayer penPlayer = playerTable.get(new PlayerUUID(minecraftProfile.getMinecraftUUID()));
        if (penPlayer == null) {
            penPlayer = new PenPlayer(plugin, new PlayerUUID(minecraftProfile.getMinecraftUUID()));
            playerTable.insert(penPlayer);
        }
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            penPlayer.switchCharacter(wrapper.getPnPCharacter());
        }
    }

    @Override
    public void updateCharacter(@NotNull RPKCharacter character) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            plugin.getDatabase().getTable(CharacterTable.class).update(wrapper.getPnPCharacter());
        }
    }

}
