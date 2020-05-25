package com.github.liamvii.penandpaper.player;

import org.bukkit.OfflinePlayer;

import java.util.Objects;
import java.util.UUID;

public final class PlayerUUID {

    private final UUID value;

    public PlayerUUID(UUID value) {
        this.value = value;
    }

    public PlayerUUID(OfflinePlayer player) {
        this(player.getUniqueId());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerUUID that = (PlayerUUID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
