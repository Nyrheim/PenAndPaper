package com.github.liamvii.penandpaper.rpkit.economy;

import com.github.liamvii.penandpaper.money.Currency;
import com.rpkit.economy.bukkit.currency.RPKCurrency;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import static com.github.liamvii.penandpaper.money.Currency.*;

public final class PenRPKCurrencyWrapper implements RPKCurrency {

    private final Currency pnpCurrency;

    public PenRPKCurrencyWrapper(Currency pnpCurrency) {
        this.pnpCurrency = pnpCurrency;
    }

    public Currency getPnPCurrency() {
        return pnpCurrency;
    }

    @Override
    public int getDefaultAmount() {
        return 0;
    }

    @Override
    public void setDefaultAmount(int amount) {

    }

    @NotNull
    @Override
    public Material getMaterial() {
        return Material.AIR;
    }

    @Override
    public void setMaterial(@NotNull Material material) {

    }

    @NotNull
    @Override
    public String getName() {
        return pnpCurrency.getName();
    }

    @Override
    public void setName(@NotNull String name) {

    }

    @NotNull
    @Override
    public String getNamePlural() {
        return pnpCurrency.getName();
    }

    @Override
    public void setNamePlural(@NotNull String namePlural) {

    }

    @NotNull
    @Override
    public String getNameSingular() {
        return pnpCurrency.getName();
    }

    @Override
    public void setNameSingular(@NotNull String nameSingular) {

    }

    @Override
    public double getRate() {
        if (pnpCurrency == PP) return 1;
        if (pnpCurrency == GP) return 10;
        if (pnpCurrency == EP) return 20;
        if (pnpCurrency == SP) return 100;
        if (pnpCurrency == CP) return 1000;
        return -1;
    }

    @Override
    public void setRate(double rate) {

    }

    @Override
    public int convert(int amount, @NotNull RPKCurrency currency) {
        return (int) Math.floor(((double) amount / getRate()) * currency.getRate());
    }

    @Override
    public int getId() {
        if (pnpCurrency == PP) return 1;
        if (pnpCurrency == GP) return 2;
        if (pnpCurrency == EP) return 3;
        if (pnpCurrency == SP) return 4;
        if (pnpCurrency == CP) return 5;
        return -1;
    }

    @Override
    public void setId(int id) {

    }
}
