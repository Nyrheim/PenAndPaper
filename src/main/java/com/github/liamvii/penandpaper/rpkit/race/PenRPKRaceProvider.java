package com.github.liamvii.penandpaper.rpkit.race;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.race.PenRaceService;
import com.github.liamvii.penandpaper.race.Race;
import com.rpkit.characters.bukkit.race.RPKRace;
import com.rpkit.characters.bukkit.race.RPKRaceProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Collectors;

public final class PenRPKRaceProvider implements RPKRaceProvider {

    private final Pen plugin;

    public PenRPKRaceProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @NotNull
    @Override
    public Collection<RPKRace> getRaces() {
        PenRaceService raceService = plugin.getServices().get(PenRaceService.class);
        return raceService.getRaces().stream()
                .map(PenRPKRaceWrapper::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addRace(@NotNull RPKRace race) {

    }

    @Nullable
    @Override
    public RPKRace getRace(int id) {
        return null;
    }

    @Nullable
    @Override
    public RPKRace getRace(@NotNull String name) {
        PenRaceService raceService = plugin.getServices().get(PenRaceService.class);
        Race penRace = raceService.getRace(name);
        if (penRace == null) return null;
        return new PenRPKRaceWrapper(penRace);
    }

    @Override
    public void removeRace(@NotNull RPKRace race) {

    }
}
