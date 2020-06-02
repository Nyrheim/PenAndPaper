package com.github.liamvii.penandpaper.rpkit.race;

import com.github.liamvii.penandpaper.race.Race;
import com.rpkit.characters.bukkit.race.RPKRace;
import org.jetbrains.annotations.NotNull;

public final class PenRPKRaceWrapper implements RPKRace {

    private final Race penRace;

    public PenRPKRaceWrapper(Race penRace) {
        this.penRace = penRace;
    }

    @NotNull
    @Override
    public String getName() {
        return penRace.getName();
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
