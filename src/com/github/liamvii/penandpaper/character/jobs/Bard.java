package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

public class Bard extends Job {

    public Bard(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Bard";
        jobLevel = 1;
        hitDie = 8;
        dieCount = 1;
        level1HP = 12;
        jobHP = 5;
    }
}
