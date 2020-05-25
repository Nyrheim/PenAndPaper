package com.github.liamvii.penandpaper.rpkit.economy;

import com.rpkit.economy.bukkit.currency.RPKCurrency;
import com.rpkit.economy.bukkit.currency.RPKCurrencyProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.github.liamvii.penandpaper.money.Currency.*;

public final class PnPCurrencyProvider implements RPKCurrencyProvider {
    @NotNull
    @Override
    public Collection<RPKCurrency> getCurrencies() {
        List<RPKCurrency> currencies = new ArrayList<>();
        currencies.add(new PnPCurrencyWrapper(PP));
        currencies.add(new PnPCurrencyWrapper(GP));
        currencies.add(new PnPCurrencyWrapper(EP));
        currencies.add(new PnPCurrencyWrapper(SP));
        currencies.add(new PnPCurrencyWrapper(CP));
        return currencies;
    }

    @Nullable
    @Override
    public RPKCurrency getDefaultCurrency() {
        return new PnPCurrencyWrapper(GP);
    }

    @Override
    public void addCurrency(@NotNull RPKCurrency currency) {

    }

    @Nullable
    @Override
    public RPKCurrency getCurrency(int id) {
        switch (id) {
            case 1: return new PnPCurrencyWrapper(PP);
            case 2: return new PnPCurrencyWrapper(GP);
            case 3: return new PnPCurrencyWrapper(EP);
            case 4: return new PnPCurrencyWrapper(SP);
            case 5: return new PnPCurrencyWrapper(CP);
        }
        return null;
    }

    @Nullable
    @Override
    public RPKCurrency getCurrency(@NotNull String name) {
        switch (name.toLowerCase()) {
            case "pp": return new PnPCurrencyWrapper(PP);
            case "gp": return new PnPCurrencyWrapper(GP);
            case "ep": return new PnPCurrencyWrapper(EP);
            case "sp": return new PnPCurrencyWrapper(SP);
            case "cp": return new PnPCurrencyWrapper(CP);
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
