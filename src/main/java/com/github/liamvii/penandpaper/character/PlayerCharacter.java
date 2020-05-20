package com.github.liamvii.penandpaper.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.jobs.Job;
import com.github.liamvii.penandpaper.character.races.Race;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import com.github.liamvii.penandpaper.conversations.StartCreate;
import com.github.liamvii.penandpaper.gui.JobGUI;
import com.github.liamvii.penandpaper.gui.LevelGUI;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static com.github.liamvii.penandpaper.utils.BukkitSerialization.*;
import static com.github.liamvii.penandpaper.utils.Exists.existsFromDB;
import static com.github.liamvii.penandpaper.utils.Insert.*;
import static com.github.liamvii.penandpaper.utils.Select.*;
import static java.lang.Integer.parseInt;

public class PlayerCharacter {

    private Pen plugin;
    final String[] SCORE_NAMES = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};

    int id;
    String uuid;
    String playerName;
    String firstName;
    String familyName;
    String height;
    String weight;
    String appearance;
    String presence;
    int age;
    int level;
    int maxHP;
    int currentHP;
    int hpMod;
    int maxHPModLevel = 0;
    int experience;
    HashMap<String, Integer> abilityScores = new HashMap<String, Integer>();
    HashMap<String, Integer> tempScores = new HashMap<>();
    HashMap<String, Integer> modifiers = new HashMap<String, Integer>();
    HashMap<String, Integer> jobList = new HashMap<>();
    ArrayList<Integer> jobIDs = new ArrayList<>();
    String raceName;

    // Constructs a new character
    public PlayerCharacter(int slotID, Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        uuid = player.getUniqueId().toString();
        playerName = player.getName();
        if (this.getCharacterExists(slotID)) { // Check if a character exists in the slot. Edge case.
            player.sendMessage("Please select an empty slot or delete a Soul.");
        }
        Pen.addCharacter(id, this);
        id = idSerialize();
        holder.setActive(id);
        holder.setChar(id, slotID);
        createConversation(player);
        Pen.addCharacter(id, this);
    }

    // Loads a new character into memory
    public PlayerCharacter(int slotID, CharacterHolder holder) {
        Pen.getCharacter(holder.getActive()).serializeInventory(holder.getPlayer());
        id = holder.getChar(slotID);
        this.deserializeCharacter();
        this.deserializeInventory(holder.getPlayer());
        Pen.addCharacter(id, this);
        holder.setActive(id);
    }

    // Loads the current active character into memory
    public PlayerCharacter(Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        id = holder.getActive();
        this.deserializeCharacter();
        Pen.addCharacter(id, this);
    }

//
    private void deserializeCharacter() {
        String vals = Integer.toString(id);
        ArrayList<String> deserialized = deserialize("SELECT firstName, familyName, age, height, weight, appearance, presence, str_base, dex_base, con_base, int_base, wis_base, cha_base, str_mod, dex_mod, con_mod, int_mod, wis_mod, cha_mod, str_temp, dex_temp, con_temp, int_temp, wis_temp, cha_temp, experience, maxHP, level, race FROM characters WHERE id = ?", vals);
        firstName = deserialized.get(0);
        familyName = deserialized.get(1);
        age = Integer.parseInt(deserialized.get(2));
        height = deserialized.get(3);
        weight = deserialized.get(4);
        appearance = deserialized.get(5);
        presence = deserialized.get(6);
        for (int x = 0; x < 6; x++) {
            abilityScores.put(SCORE_NAMES[x], Integer.parseInt(deserialized.get(x+7)));
        }
        for (int y = 0; y < 6; y++) {
            modifiers.put(SCORE_NAMES[y], Integer.parseInt(deserialized.get(y+13)));
        }
        for (int z = 0; z < 6; z++) {
            tempScores.put(SCORE_NAMES[z], Integer.parseInt(deserialized.get(z+19)));
        }
        experience = Integer.parseInt(deserialized.get(25));
        ArrayList<String> jobids = deserialize("SELECT jobID1, jobID2, jobID3 FROM characters WHERE id = ?", vals);
        assert jobids != null;
        if (Integer.parseInt(jobids.get(0)) > -1) {
            Job job1 = new Job(Integer.parseInt(jobids.get(0)));
            this.setJob(Integer.parseInt(jobids.get(0)), job1);
        }
        if (jobids.size() >= 2) {
            if (Integer.parseInt(jobids.get(1)) > -1) {
                Job job1 = new Job(Integer.parseInt(jobids.get(1)));
                this.setJob(Integer.parseInt(jobids.get(1)), job1);
            }
        }
        if (jobids.size() >= 3) {
            if (parseInt(jobids.get(2)) > -1) {
                Job job1 = new Job(parseInt(jobids.get(2)));
                this.setJob(Integer.parseInt(jobids.get(2)), job1);
            }
        }
        jobIDs = this.pullJobIDs();
        maxHP = Integer.parseInt(deserialized.get(26));
        level = calculateLevel(jobIDs);
        raceName = deserialized.get(27);
    }

    public void storeChar(int slotID, CharacterHolder holder) {
        Player player = holder.getPlayer();
        ArrayList<String> vals = new ArrayList<>();
        vals.add(player.getUniqueId().toString());
    }

    public void delChar(int slotID, CharacterHolder holder) {
        Player player = holder.getPlayer();
        ArrayList<String> vals = new ArrayList<>();
        vals.add(player.getUniqueId().toString());
        insertToDB("UPDATE characterHolder SET charSlot" + slotID + " = -1 WHERE uuid = ?", vals);
        Pen.delCharacter(id);
        holder.setActive(-2);
    }

    public void delChar(Player player) {
        CharacterHolder holder = Pen.getHolder(player);

    }

    private void createConversation(Player player) {
        ConversationFactory cf = new ConversationFactory(plugin);
        Conversation conv = cf.withFirstPrompt(new StartCreate()).withLocalEcho(false).buildConversation(player);
        conv.begin();
    }

    // Could (should?) be moved into levelUp()
    private int calculateLevelMaxHP(ArrayList<Integer> jobIDs) {
        int size = jobIDs.size();
        for (int i = 0; i < size; i++) {
            maxHP = maxHP + Pen.getJob(jobIDs.get(i)).getJobHP();
        }
        return maxHP;
    }

    // Could (should?) be moved into levelUp()
    private int calculateLevel(ArrayList<Integer> jobIDs) {
        int size = jobIDs.size();
        for (int i = 0; i < size; i++) {
            level = level + Pen.getJob(jobIDs.get(i)).getJobLevel();
        }
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(level)); // Move db code to setLevel() for internal consistency
        vals.add(Integer.toString(id));
        insertToDB("UPDATE characters SET level = ? WHERE id = ?", vals);
        return level;
    }

    public void levelUp(Player player) {
        LevelGUI createLevelGUI = new LevelGUI();
        createLevelGUI.initializeItems(player);
        createLevelGUI.openInventory(player);
    }

    private boolean getCharacterExists(int slot) {
        String[] vals = {uuid};
        if (!(existsFromDB("SELECT * FROM characterHolder WHERE uuid = ? AND charSlot" + slot + " != -1", vals))) {
            return false;
        }
        else {
            return true;
        }
    }

    private int idSerialize() {
        ArrayList<String> vals = new ArrayList<>();
        vals.add(uuid);
        vals.add(playerName);
        id = insertReturnID("INSERT INTO characters (uuid, jobID1, jobID2, jobID3, experience, playerName, hpMod, maxHPModLevel) VALUES (?, -1, -1, -1, 0, ?, 0, 0)", vals);
        return id;
    }

    // Getters

    // Return Job in slot for character.
    public int getJobID(int slotID) {
        return selectInt("SELECT jobID" + slotID + " FROM characters WHERE id = ?", Integer.toString(id));
    }

    // Return all Job IDs for multiclass support.
    public ArrayList<Integer> pullJobIDs() {
        String vals = Integer.toString(id);
        ArrayList<String> jobList = deserialize("SELECT jobID1, jobID2, jobID3 FROM characters WHERE id = ?", vals);
        ArrayList<Integer> jobIDs = new ArrayList<>();
        if (Integer.parseInt(jobList.get(0)) > -1) {
            jobIDs.add(Integer.parseInt(jobList.get(0)));
        }
        if (Integer.parseInt(jobList.get(1)) > -1) {
            jobIDs.add(Integer.parseInt(jobList.get(1)));
        }
        if (Integer.parseInt(jobList.get(2)) > -1) {
            jobIDs.add(Integer.parseInt(jobList.get(2)));
        }
        return jobIDs;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getPresence() {
        return presence;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getHPMod() {
        return hpMod;
    }

    public int getEXP() {
        return experience;
    }

    public HashMap<String, Integer> getAbilityScores() {
        return abilityScores;
    }

    public HashMap<String, Integer> getTempScores() {
        return tempScores;
    }

    public HashMap<String, Integer> getModifiers() {
        return modifiers;
    }

    public HashMap<String, Integer> getJobList() {
        return jobList;
    }

    public ArrayList<Integer> getJobIDs() {
        return jobIDs;
    }

    public String getRaceName() {
        return raceName;
    }

    // Setters

    public void setFirstName(String fName) {
        firstName = fName;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(firstName);
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET firstName = ? WHERE id = ?", vals);
    }

    public void setFamilyName(String fName) {
        firstName = fName;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(firstName);
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET familyName = ? WHERE id = ?", vals);
    }

    public void setAge(int a) {
        age = a;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(age));
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET age = ? WHERE id = ?", vals);
    }

    public void setHeight(String h) {
        height = h;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(height);
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET height = ? WHERE id = ?", vals);
    }

    public void setWeight(String w) {
        weight = w;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(weight);
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET weight = ? WHERE id = ?", vals);
    }

    public void setAppearance(String app) {
        appearance = app;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(appearance);
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET appearance = ? WHERE id = ?", vals);
    }

    public void setMaxHP(int max) {
        maxHP = max;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(maxHP));
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET maxHP = ? WHERE id = ?", vals);
    }

    public void setCurrentHP(int current) {
        currentHP = current;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(currentHP));
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET currentHP = ? WHERE id = ?", vals);
    }

    public void incrementCurrentHP(int inc) {
        currentHP = currentHP + inc;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(currentHP));
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET currentHP = ? WHERE id = ?", vals);
    }

    public void decrementCurrentHP(int dec) {
        currentHP = currentHP - dec;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(currentHP));
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET currentHP = ? WHERE id = ?", vals);
    }

    public void setPresence(String pre) {
        presence = pre;
        ArrayList<String> vals = new ArrayList<>();
        vals.add(presence);
        vals.add(Integer.toString(id));
        serialize("UPDATE characters SET presence = ? WHERE id = ?", vals);
    }
// TODO: Serialize all setter methods
    public void incrementEXP(int exp, Player player) {
        experience = experience + exp;
        if (experience > Pen.getLevel(level)) {
            this.levelUp(player);
        }
    }


    public void decrementEXP(int exp) {
        experience = experience - exp;
    }

    public void setEXP(int exp) {
        experience = exp;
    }

    public void updateAbilityScore(String score, int update) {
        int currentScore = abilityScores.get(score);
        abilityScores.remove(score);
        abilityScores.put(score, currentScore + update);
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(abilityScores.get(score)));
        vals.add(Integer.toString(id));
        double mod = ((abilityScores.get(score) - 10) / 2);
        Integer modInt = (int) Math.floor(mod);
        modifiers.put(score, modInt);
        ArrayList<String> vals1 = new ArrayList<>();
        vals1.add(Integer.toString(modifiers.get(score)));
        vals1.add(Integer.toString(id));
        System.out.print(abilityScores.get(score));
        insertToDB("UPDATE characters SET " + score + "_base = ? WHERE id = ?", vals);
        insertToDB("UPDATE characters SET " + score + "_mod = ? WHERE id = ?", vals1);
    }

    public void setFreshAbilityScores(int[] pointarray) {
        abilityScores.put("STR", pointarray[0]);
        abilityScores.put("DEX", pointarray[1]);
        abilityScores.put("CON", pointarray[2]);
        abilityScores.put("INT", pointarray[3]);
        abilityScores.put("WIS", pointarray[4]);
        abilityScores.put("CHA", pointarray[5]);
        for (int i = 0; i < 6; i++) {
            double mod = ((pointarray[i] - 10) / 2);
            Integer modInt = (int) Math.floor(mod);
            modifiers.put(SCORE_NAMES[i], modInt);
        }
        for (int x = 0; x < 6; x++) {
            tempScores.put(SCORE_NAMES[x], 0);
        }
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(pointarray[0]));
        vals.add(Integer.toString(pointarray[1]));
        vals.add(Integer.toString(pointarray[2]));
        vals.add(Integer.toString(pointarray[3]));
        vals.add(Integer.toString(pointarray[4]));
        vals.add(Integer.toString(pointarray[5]));
        vals.add(Integer.toString(modifiers.get("STR")));
        vals.add(Integer.toString(modifiers.get("DEX")));
        vals.add(Integer.toString(modifiers.get("CON")));
        vals.add(Integer.toString(modifiers.get("INT")));
        vals.add(Integer.toString(modifiers.get("WIS")));
        vals.add(Integer.toString(modifiers.get("CHA")));
        vals.add(Integer.toString(tempScores.get("STR")));
        vals.add(Integer.toString(tempScores.get("DEX")));
        vals.add(Integer.toString(tempScores.get("CON")));
        vals.add(Integer.toString(tempScores.get("INT")));
        vals.add(Integer.toString(tempScores.get("WIS")));
        vals.add(Integer.toString(tempScores.get("CHA")));
        vals.add(Integer.toString(id));
        insertToDB("UPDATE characters SET str_base = ?, dex_base = ?, con_base = ?, int_base = ?, wis_base = ?, cha_base = ?, str_mod = ?, dex_mod = ?, con_mod = ?, int_mod = ?, wis_mod = ?, cha_mod = ?, str_temp = ?, dex_temp = ?, con_temp = ?, int_temp = ?, wis_temp = ?, cha_temp = ? WHERE id = ?", vals);
    }

    public void setJob(int jobID, Job job) {
        if (jobID > -1) {
            if (!(jobList.containsKey(job.getJobName()))) {
                jobList.put(job.getJobName(), jobID);
            }
            for (int i = 0; i < jobList.size(); i++) {
                ArrayList<String> vals = new ArrayList<>();
                vals.add(Integer.toString(jobID));
                vals.add(Integer.toString(id));
                int x = i+1;
                insertToDB("UPDATE characters SET jobID" + x + " = ? WHERE ID = ?", vals);
            }
        }
    }

    public void serializeInventory(Player player) {
        Inventory inv = player.getInventory();
        ItemStack[] stack = inv.getContents();
        String stackString = itemStackArrayToBase64(stack);
        ArrayList<String> vals = new ArrayList<>();
        vals.add(stackString);
        vals.add(Integer.toString(id));
        insertToDB("UPDATE characters SET inventory = ? WHERE id = ?", vals);
    }

    public void deserializeInventory(Player player) {
        String val = Integer.toString(id);
        String stackString = selectString("SELECT inventory FROM characters WHERE id = ?", val);
        try {
            ItemStack[] stack = itemStackArrayFromBase64(stackString);
            player.getInventory().setContents(stack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
