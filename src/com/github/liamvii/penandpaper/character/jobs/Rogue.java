package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Rogue extends Job {

    public Rogue(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Rogue";
        jobLevel = 1;
        hitDie = 8;
        dieCount = 1;
        level1HP = 12;
        jobHP = 5;
    }
}
