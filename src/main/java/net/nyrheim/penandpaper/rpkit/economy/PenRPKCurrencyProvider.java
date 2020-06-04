package net.nyrheim.penandpaper.rpkit.economy;

import com.rpkit.economy.bukkit.currency.RPKCurrency;
import com.rpkit.economy.bukkit.currency.RPKCurrencyProvider;
import net.nyrheim.penandpaper.money.Currency;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PenRPKCurrencyProvider implements RPKCurrencyProvider {
    @NotNull
    @Override
    public Collection<RPKCurrency> getCurrencies() {
        List<RPKCurrency> currencies = new ArrayList<>();
        currencies.add(new PenRPKCurrencyWrapper(Currency.PP));
        currencies.add(new PenRPKCurrencyWrapper(Currency.GP));
        currencies.add(new PenRPKCurrencyWrapper(Currency.EP));
        currencies.add(new PenRPKCurrencyWrapper(Currency.SP));
        currencies.add(new PenRPKCurrencyWrapper(Currency.CP));
        return currencies;
    }

    @Nullable
    @Override
    public RPKCurrency getDefaultCurrency() {
        return new PenRPKCurrencyWrapper(Currency.GP);
    }

    @Override
    public void addCurrency(@NotNull RPKCurrency currency) {

    }

    @Nullable
    @Override
    public RPKCurrency getCurrency(int id) {
        switch (id) {
            case 1: return new PenRPKCurrencyWrapper(Currency.PP);
            case 2: return new PenRPKCurrencyWrapper(Currency.GP);
            case 3: return new PenRPKCurrencyWrapper(Currency.EP);
            case 4: return new PenRPKCurrencyWrapper(Currency.SP);
            case 5: return new PenRPKCurrencyWrapper(Currency.CP);
        }
        return null;
    }

    @Nullable
    @Override
    public RPKCurrency getCurrency(@NotNull String name) {
        switch (name.toLowerCase()) {
            case "pp": return new PenRPKCurrencyWrapper(Currency.PP);
            case "gp": return new PenRPKCurrencyWrapper(Currency.GP);
            case "ep": return new PenRPKCurrencyWrapper(Currency.EP);
            case "sp": return new PenRPKCurrencyWrapper(Currency.SP);
            case "cp": return new PenRPKCurrencyWrapper(Currency.CP);
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
