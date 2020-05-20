package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Barbarian extends Job {

    public Barbarian(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Barbarian";
        jobLevel = 1;
        hitDie = 12;
        dieCount = 1;
        level1HP = 16;
        jobHP = 7;
    }
}
