package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Sorcerer extends Job {

    public Sorcerer(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Sorcerer";
        jobLevel = 1;
        hitDie = 6;
        dieCount = 1;
        level1HP = 10;
        jobHP = 4;
    }
}
