package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.characterholder.CharacterHolder;

import java.util.ArrayList;

import static com.github.liamvii.penandpaper.utils.Select.deserialize;

public class Fighter extends Job {

    public Fighter(int activeID, CharacterHolder holder) {
        charID = activeID;
        jobName = "Fighter";
        jobLevel = 1;
        hitDie = 10;
        dieCount = 1;
        level1HP = 14;
        jobHP = 14;
     //   freshSerialize(holder.getPlayer());
    }



}