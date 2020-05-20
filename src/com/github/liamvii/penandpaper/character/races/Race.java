package com.github.liamvii.penandpaper.character.races;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static com.github.liamvii.penandpaper.utils.Insert.insertToDB;

public class Race {

    public HashMap<String, Integer> raceASI = new HashMap<>();
    int ASILimit;
    String raceName;
    int maxHPMod = 0;

    public void serialize(Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        PlayerCharacter character = Pen.getCharacter(holder.getActive());
        character.updateAbilityScore("STR", raceASI.get("STR"));
        character.updateAbilityScore("DEX", raceASI.get("DEX"));
        character.updateAbilityScore("CON", raceASI.get("CON"));
        character.updateAbilityScore("INT", raceASI.get("INT"));
        character.updateAbilityScore("WIS", raceASI.get("WIS"));
        character.updateAbilityScore("CHA", raceASI.get("CHA"));
        ArrayList<String> vals = new ArrayList<>();
        vals.add(raceName);
        vals.add(Integer.toString(maxHPMod));
        vals.add(Integer.toString(holder.getActive()));
        insertToDB("UPDATE characters SET race = ?, maxHPModLevel = ? WHERE id = ?", vals);
    }

    public void setMaxHPMod() {

    }




}
