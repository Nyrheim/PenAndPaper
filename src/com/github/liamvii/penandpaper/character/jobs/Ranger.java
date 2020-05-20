package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Ranger extends Job {

    public Ranger(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Ranger";
        jobLevel = 1;
        hitDie = 10;
        dieCount = 1;
        level1HP = 14;
        jobHP = 6;
    }
}
