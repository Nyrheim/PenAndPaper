package net.nyrheim.penandpaper.clazz;

public final class CharacterClass {

    private final PenClass clazz;
    private int level;

    public CharacterClass(PenClass clazz, int level) {
        this.clazz = clazz;
        this.level = level;
    }

    public CharacterClass(PenClass clazz) {
        this(clazz, 1);
    }

    public PenClass getClazz() {
        return clazz;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
