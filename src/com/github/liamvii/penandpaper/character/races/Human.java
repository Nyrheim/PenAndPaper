package com.github.liamvii.penandpaper.character.races;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Human extends Race {

    public Human() {
        raceASI.put("STR", 1);
        raceASI.put("DEX", 1);
        raceASI.put("CON", 1);
        raceASI.put("INT", 1);
        raceASI.put("WIS", 1);
        raceASI.put("CHA", 1);
        ASILimit = 2;
        raceName = "Human";
    }

    public void applyASI(Player player) {

    }

}
