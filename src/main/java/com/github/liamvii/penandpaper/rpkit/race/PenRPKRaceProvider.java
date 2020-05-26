package com.github.liamvii.penandpaper.rpkit.race;

import com.rpkit.characters.bukkit.race.RPKRace;
import com.rpkit.characters.bukkit.race.RPKRaceProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

public final class PenRPKRaceProvider implements RPKRaceProvider {
    @NotNull
    @Override
    public Collection<RPKRace> getRaces() {
        return new ArrayList<>();
    }

    @Override
    public void addRace(@NotNull RPKRace race) {

    }

    @Nullable
    @Override
    public RPKRace getRace(int race) {
        return null;
    }

    @Nullable
    @Override
    public RPKRace getRace(@NotNull String name) {
        return new PenRPKRaceWrapper(name);
    }

    @Override
    public void removeRace(@NotNull RPKRace race) {

    }
}
