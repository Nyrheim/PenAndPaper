package com.github.liamvii.penandpaper.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.conversations.StartCreate;
import com.github.liamvii.penandpaper.utils.ConnectionManager;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.HashMap;

import static com.github.liamvii.penandpaper.utils.BukkitSerialization.itemStackArrayToBase64;
import static com.github.liamvii.penandpaper.utils.generated.Tables.CHARACTERS;
import static org.jooq.SQLDialect.MYSQL;

public class PlayerCharacter {

    private Pen plugin;

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
    int maxHPModLevel = 0;
    int experience;
    HashMap<String, Integer> abilityScores = new HashMap<String, Integer>();
    HashMap<String, Integer> tempScores = new HashMap<>();
    HashMap<String, Integer> modifiers = new HashMap<String, Integer>();
    String raceName;

    // Constructs a new character
    public PlayerCharacter(Player player) {
        uuid = player.getUniqueId().toString();

    }

    // Loads the current active character into memory
    public PlayerCharacter() {
        this.deserializeCharacter();
    }

//
    private void deserializeCharacter() {
        DSLContext read = DSL.using(ConnectionManager.getRead(), MYSQL);
        Result<Record> result = read.select().from(CHARACTERS).where(CHARACTERS.UUID.eq(uuid)).fetch();
        for (Record r : result) {
            firstName = r.getValue(CHARACTERS.FIRSTNAME);
            familyName = r.getValue(CHARACTERS.FAMILYNAME);
            height = r.getValue(CHARACTERS.HEIGHT);
            weight = r.getValue(CHARACTERS.WEIGHT);
            appearance = r.getValue(CHARACTERS.APPEARANCE);
            presence = r.getValue(CHARACTERS.PRESENCE);
            age = Integer.parseInt(r.getValue(CHARACTERS.AGE));
        }
    }

    public void storeCharacter() {

    }

    public void delChararacter() {

    }

    private void createConversation(Player player) {
        ConversationFactory cf = new ConversationFactory(plugin);
        Conversation conv = cf.withFirstPrompt(new StartCreate()).withLocalEcho(false).buildConversation(player);
        conv.begin();
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

    public String getRaceName() {
        return raceName;
    }

    // Setters

    public void setFirstName(String fName) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.FIRSTNAME, fName).where(CHARACTERS.UUID.eq(uuid));
        firstName = fName;
    }

    public void setFamilyName(String fName) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.FAMILYNAME, fName).where(CHARACTERS.UUID.eq(uuid));
        familyName = fName;
    }

    public void setAge(int a) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.AGE, Integer.toString(age)).where(CHARACTERS.UUID.eq(uuid));
        age = a;
    }

    public void setHeight(String h) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.HEIGHT, h).where(CHARACTERS.UUID.eq(uuid));
        height = h;
    }

    public void setWeight(String w) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.WEIGHT, w).where(CHARACTERS.UUID.eq(uuid));
        weight = w;
    }

    public void setAppearance(String app) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.APPEARANCE, app).where(CHARACTERS.UUID.eq(uuid));
        appearance = app;
    }

    public void setPresence(String pre) {
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        write.update(CHARACTERS).set(CHARACTERS.PRESENCE, pre).where(CHARACTERS.UUID.eq(uuid));
        presence = pre;
    }


    public void updateAbilityScore(String score, int update) {

    }

    public void setFreshAbilityScores(int[] pointarray) {
    }

    public void setJob(int jobID) {

    }

    public void serializeInventory(Player player) {
        Inventory inv = player.getInventory();
        ItemStack[] stack = inv.getContents();
        String stackString = itemStackArrayToBase64(stack);
        ArrayList<String> vals = new ArrayList<>();
        vals.add(stackString);
        vals.add(Integer.toString(id));
     //   insertToDB("UPDATE characters SET inventory = ? WHERE id = ?", vals);
    }

    /*
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

     */

}
