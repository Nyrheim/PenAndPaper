package net.nyrheim.penandpaper.race;

public final class RaceTrait {

    private final String name;
    private final String description;

    public RaceTrait(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
