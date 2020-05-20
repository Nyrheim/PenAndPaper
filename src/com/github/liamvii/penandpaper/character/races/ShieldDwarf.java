package com.github.liamvii.penandpaper.character.races;

public class ShieldDwarf extends Race {

    public ShieldDwarf() {
        raceASI.put("STR", 2);
        raceASI.put("DEX", 0);
        raceASI.put("CON", 2);
        raceASI.put("INT", 0);
        raceASI.put("WIS", 0);
        raceASI.put("CHA", 0);
        ASILimit = 2;
        raceName = "Gold Dwarf";
    }

}
