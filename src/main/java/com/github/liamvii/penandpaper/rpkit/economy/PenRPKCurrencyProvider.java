package com.github.liamvii.penandpaper.rpkit.economy;

import com.rpkit.economy.bukkit.currency.RPKCurrency;
import com.rpkit.economy.bukkit.currency.RPKCurrencyProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.github.liamvii.penandpaper.money.Currency.*;

public final class PenRPKCurrencyProvider implements RPKCurrencyProvider {
    @NotNull
    @Override
    public Collection<RPKCurrency> getCurrencies() {
        List<RPKCurrency> currencies = new ArrayList<>();
        currencies.add(new PenRPKCurrencyWrapper(PP));
        currencies.add(new PenRPKCurrencyWrapper(GP));
        currencies.add(new PenRPKCurrencyWrapper(EP));
        currencies.add(new PenRPKCurrencyWrapper(SP));
        currencies.add(new PenRPKCurrencyWrapper(CP));
        return currencies;
    }

    @Nullable
    @Override
    public RPKCurrency getDefaultCurrency() {
        return new PenRPKCurrencyWrapper(GP);
    }

    @Override
    public void addCurrency(@NotNull RPKCurrency currency) {

    }

    @Nullable
    @Override
    public RPKCurrency getCurrency(int id) {
        switch (id) {
            case 1: return new PenRPKCurrencyWrapper(PP);
            case 2: return new PenRPKCurrencyWrapper(GP);
            case 3: return new PenRPKCurrencyWrapper(EP);
            case 4: return new PenRPKCurrencyWrapper(SP);
            case 5: return new PenRPKCurrencyWrapper(CP);
        }
        return null;
    }

    @Nullable
    @Override
    public RPKCurrency getCurrency(@NotNull String name) {
        switch (name.toLowerCase()) {
            case "pp": return new PenRPKCurrencyWrapper(PP);
            case "gp": return new PenRPKCurrencyWrapper(GP);
            case "ep": return new PenRPKCurrencyWrapper(EP);
            case "sp": return new PenRPKCurrencyWrapper(SP);
            case "cp": return new PenRPKCurrencyWrapper(CP);
        }
        return null;
    }

    @Override
    public void removeCurrency(@NotNull RPKCurrency rpkCurrency) {

    }

    @Override
    public void updateCurrency(@NotNull RPKCurrency rpkCurrency) {

    }
}
