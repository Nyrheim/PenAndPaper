package com.github.liamvii.penandpaper.clazz;

public final class CharacterClass {

    private final DnDClass clazz;
    private int level;

    public CharacterClass(DnDClass clazz, int level) {
        this.clazz = clazz;
        this.level = level;
    }

    public CharacterClass(DnDClass clazz) {
        this(clazz, 1);
    }

    public DnDClass getClazz() {
        return clazz;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
