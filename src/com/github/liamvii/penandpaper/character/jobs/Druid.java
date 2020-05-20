package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Druid extends Job {

    public Druid(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Druid";
        jobLevel = 1;
        hitDie = 8;
        dieCount = 1;
        level1HP = 12;
        jobHP = 5;
    }
}
