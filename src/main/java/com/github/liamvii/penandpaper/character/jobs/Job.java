package com.github.liamvii.penandpaper.character.jobs;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.github.liamvii.penandpaper.utils.Insert.insertReturnID;
import static com.github.liamvii.penandpaper.utils.Insert.insertToDB;
import static com.github.liamvii.penandpaper.utils.Select.deserialize;

public class Job {

    int jobID = -1;
    int charID = 0;
    String jobName = "";
    int jobLevel = 1;
    int hitDie;
    int dieCount;
    int level1HP;
    int jobHP;

    public Job() {

    }

    public Job(int jobID) {
        String vals = Integer.toString(jobID);
        ArrayList<String> deserialized = deserialize("SELECT id, jobName, jobLevel, hitDie, dieCount FROM jobs WHERE id = ?", vals);
        assert deserialized != null;
        jobID = Integer.parseInt(deserialized.get(0));
        jobName = deserialized.get(1);
        jobLevel = Integer.parseInt(deserialized.get(2));
        hitDie = Integer.parseInt(deserialized.get(3));
        dieCount = Integer.parseInt(deserialized.get(4));
        Pen.addJob(jobID, this);
    }

    public void freshSerialize(Player player) {
        ArrayList<String> vals = new ArrayList<>();
        ArrayList<String> valsChar = new ArrayList<>();
        vals.add(Integer.toString(charID));
        vals.add(jobName);
        vals.add(Integer.toString(jobLevel));
        vals.add(Integer.toString(hitDie));
        vals.add(Integer.toString(dieCount));
        vals.add(Integer.toString(jobHP));
        jobID = insertReturnID("INSERT INTO jobs (charID, jobName, jobLevel, hitDie, dieCount, jobHP) VALUES (?, ?, ?, ?, ?, ?)", vals);
        valsChar.add(Integer.toString(level1HP));
        valsChar.add(Integer.toString(charID));
        insertToDB("UPDATE characters SET maxHP = ? WHERE id = ?", valsChar);
        PlayerCharacter character = Pen.getCharacter(Pen.getHolder(player).getActive());
        character.setJob(jobID, this);
    }

    public void serialize(Player player) {
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(jobHP));
        vals.add(Integer.toString(jobLevel));
        vals.add(Integer.toString(jobID));
        vals.add(Integer.toString(dieCount));
        insertToDB("UPDATE jobs SET jobHP = ? jobLevel = ? dieCount = ? WHERE id = ?", vals);
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobID() {
        return jobID;
    }

    public int getJobLevel() {
        return jobLevel;
    }

    public int getCharID() {
        return charID;
    }

    public int getHitDie() {
        return hitDie;
    }

    public int getDieCount() {
        return dieCount;
    }

    public int getLevel1HP() {
        return level1HP;
    }

    public int getJobHP() {
        return jobHP;
    }

    // Setters


    public void setJobLevel(int level) {
        jobLevel = level;
    }

    public void setJobHP(int hp) {
        jobHP = jobHP + hp;
    }

    public void setDieCount(int count) {
        dieCount = dieCount + count;
    }

    public void jobLevelUp() {
        jobLevel = jobLevel + 1;
        dieCount = jobLevel * 1;
    }
}
