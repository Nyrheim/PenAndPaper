package com.github.liamvii.penandpaper.player;

import org.bukkit.OfflinePlayer;

import java.util.Objects;
import java.util.UUID;

public final class PlayerId {

    private final UUID value;

    public PlayerId(UUID value) {
        this.value = value;
    }

    public PlayerId(OfflinePlayer player) {
        this(player.getUniqueId());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerId playerId = (PlayerId) o;
        return Objects.equals(value, playerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
