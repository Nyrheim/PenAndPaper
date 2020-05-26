package com.github.liamvii.penandpaper.rpkit.stat;

import com.github.liamvii.penandpaper.ability.Ability;
import com.rpkit.stats.bukkit.stat.RPKStat;
import com.rpkit.stats.bukkit.stat.RPKStatProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PenRPKStatProvider implements RPKStatProvider {
    @NotNull
    @Override
    public List<RPKStat> getStats() {
        return Arrays.stream(Ability.values())
                .map(PenRPKStatWrapper::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addStat(@NotNull RPKStat stat) {

    }

    @Nullable
    @Override
    public RPKStat getStat(@NotNull String name) {
        try {
            Ability ability = Ability.valueOf(name.toUpperCase());
            return new PenRPKStatWrapper(ability);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    @Override
    public void removeStat(@NotNull RPKStat rpkStat) {

    }
}
