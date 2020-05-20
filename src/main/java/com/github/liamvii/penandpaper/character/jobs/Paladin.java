package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Paladin extends Job {

    public Paladin(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Paladin";
        jobLevel = 1;
        hitDie = 10;
        dieCount = 1;
        level1HP = 14;
        jobHP = 6;
    }
}
