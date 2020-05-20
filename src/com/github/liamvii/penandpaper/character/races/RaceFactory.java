package com.github.liamvii.penandpaper.character.races;
import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.jobs.*;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RaceFactory {

    Map<Integer, Race> raceMap = new HashMap<>();

    public RaceFactory(Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        int activeID = holder.getActive();
        raceMap.put(0, new Human());
        // Doesn't currently check for the slot clicked, just adds things to the hash map!
        // Use the below method to do that on the JobFactory instance? Analyze code structure, I can't remember.
    }

    public Race getObject(int slotClicked, Player player) {
        player.closeInventory();
        return raceMap.get(slotClicked);
    }

}