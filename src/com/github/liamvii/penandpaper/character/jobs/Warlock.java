package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Warlock extends Job {

    public Warlock(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Warlock";
        jobLevel = 1;
        hitDie = 8;
        dieCount = 1;
        level1HP = 12;
        jobHP = 5;
    }
}
