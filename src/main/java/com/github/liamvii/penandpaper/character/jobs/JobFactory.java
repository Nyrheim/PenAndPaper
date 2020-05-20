package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class JobFactory {

    Map<Integer, Job> jobMap = new HashMap<>();

    public JobFactory(Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        int activeID = holder.getActive();
        jobMap.put(0, new Fighter(activeID, holder));
        jobMap.put(1, new Ranger(activeID, holder));
        jobMap.put(2, new Rogue(activeID, holder));
        jobMap.put(3, new Barbarian(activeID, holder));
        jobMap.put(4, new Monk(activeID, holder));
        jobMap.put(5, new Bard(activeID, holder));
        jobMap.put(6, new Wizard(activeID, holder));
        jobMap.put(7, new Sorcerer(activeID, holder));
        jobMap.put(8, new Warlock(activeID, holder));
        jobMap.put(12, new Druid(activeID, holder));
        jobMap.put(13, new Cleric(activeID, holder));
        jobMap.put(14, new Paladin(activeID, holder));
        // Doesn't currently check for the slot clicked, just adds things to the hash map!
        // Use the below method to do that on the JobFactory instance? Analyze code structure, I can't remember.
    }

    public Job getObject(int slotClicked, Player player) {
        player.closeInventory();
        return jobMap.get(slotClicked);
    }

}
