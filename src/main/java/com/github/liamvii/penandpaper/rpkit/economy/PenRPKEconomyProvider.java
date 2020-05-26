package com.github.liamvii.penandpaper.rpkit.economy;

import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.economy.bukkit.currency.RPKCurrency;
import com.rpkit.economy.bukkit.economy.RPKEconomyProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class PenRPKEconomyProvider implements RPKEconomyProvider {

    @Override
    public int getBalance(@NotNull RPKCharacter character, @NotNull RPKCurrency currency) {
        return 0;
    }

    @NotNull
    @Override
    public List<RPKCharacter> getRichestCharacters(@NotNull RPKCurrency currency, int amount) {
        return new ArrayList<>();
    }

    @Override
    public void setBalance(@NotNull RPKCharacter character, @NotNull RPKCurrency currency, int amount) {

    }

    @Override
    public void transfer(@NotNull RPKCharacter from, @NotNull RPKCharacter to, @NotNull RPKCurrency currency, int amount) {

    }

}
