package com.github.liamvii.penandpaper.money;

public final class Money {

    private final int amount;
    private final Currency currency;

    public Money(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

}
