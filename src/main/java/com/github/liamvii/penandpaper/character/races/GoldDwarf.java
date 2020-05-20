package com.github.liamvii.penandpaper.character.races;

public class GoldDwarf extends Race {

    public GoldDwarf() {
        raceASI.put("STR", 0);
        raceASI.put("DEX", 0);
        raceASI.put("CON", 2);
        raceASI.put("INT", 0);
        raceASI.put("WIS", 1);
        raceASI.put("CHA", 0);
        ASILimit = 2;
        raceName = "Gold Dwarf";
        maxHPMod = 1;
    }



}
