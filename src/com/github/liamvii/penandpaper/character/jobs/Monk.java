package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Monk extends Job {

    public Monk(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Monk";
        jobLevel = 1;
        hitDie = 8;
        dieCount = 1;
        level1HP = 12;
        jobHP = 5;
    }
}
