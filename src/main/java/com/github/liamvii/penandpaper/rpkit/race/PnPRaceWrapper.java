package com.github.liamvii.penandpaper.rpkit.race;

import com.rpkit.characters.bukkit.race.RPKRace;
import org.jetbrains.annotations.NotNull;

public final class PnPRaceWrapper implements RPKRace {

    private final String raceName;

    public PnPRaceWrapper(String raceName) {
        this.raceName = raceName;
    }

    @NotNull
    @Override
    public String getName() {
        return raceName;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
