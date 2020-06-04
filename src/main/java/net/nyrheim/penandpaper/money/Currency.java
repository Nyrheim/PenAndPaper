package net.nyrheim.penandpaper.money;

public final class Currency {

    public static Currency PP = new Currency("pp");
    public static Currency GP = new Currency("gp");
    public static Currency EP = new Currency("ep");
    public static Currency SP = new Currency("sp");
    public static Currency CP = new Currency("cp");

    private final String name;

    private Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
