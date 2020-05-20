package com.github.liamvii.penandpaper.characterholder;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.github.liamvii.penandpaper.utils.Exists.existsFromDB;
import static com.github.liamvii.penandpaper.utils.Insert.insertToDB;
import static com.github.liamvii.penandpaper.utils.Select.selectCharSlots;
import static com.github.liamvii.penandpaper.utils.Select.selectInt;

public class CharacterHolder {

    private Player player;
    String uuid;
    String playerName;
    ArrayList<Integer> charSlots;
    ArrayList<Integer> storageSlots;
    int active;

    public CharacterHolder(Player player) {
        this.player = player;
        uuid = player.getUniqueId().toString();
        playerName = player.getName();
        if (!this.getExists()) {
            createHolder();
        }
        this.setCharSlots();
        this.setStorageSlots();
        Pen.addHolder(player, this);
    }

    private void createHolder() {
        ArrayList<String> vals = new ArrayList<>();
        vals.add(uuid);
        vals.add(playerName);
        insertToDB("INSERT INTO characterholder (UUID, playerName, charSlot1, charSlot2, charSlot3, storageSlot1, storageSlot2, storageSlot3, active) VALUES (?, ?, -1, -1, -1, -1, -1, -1, -2)", vals);
    }

    private boolean getExists() {
        if (!(existsFromDB("SELECT * FROM characterHolder WHERE uuid = ?", uuid))) {
            return false;
        }
        else {
            return true;
        }
    }

    // Getters

    public ArrayList<Integer> getCharSlots() {
        ArrayList<Integer> slots;
        slots = selectCharSlots("SELECT charSlot1, charSlot2, charSlot3 FROM characterHolder WHERE uuid = ?", uuid);
        return slots;
    }

    public int getChar(int slotID) {
        return selectInt("SELECT charSlot" + slotID + " FROM characterHolder WHERE uuid = ?", uuid);
    }

    public int getStoredChar(int slotID) {
        return selectInt("SELECT storageSlot" + slotID + " FROM characterHolder WHERE uuid = ?", uuid);
    }

    public ArrayList<Integer> getStorageSlots() {
        ArrayList<Integer> slots;
        slots = selectCharSlots("SELECT storageSlot1, storageSlot2, storageSlot3 FROM characterHolder WHERE uuid = ?", uuid);
        return slots;
    }

    public int getActive() {
        int active = selectInt("SELECT active FROM characterHolder WHERE uuid = ?", uuid);
        return active;
    }

    public Player getPlayer() {
        return player;
    }

    // Setters

    public void setCharSlots() {
        charSlots = this.getCharSlots();
    }

    public void setChar(int charID, int slotID) {
        ArrayList<String> set = new ArrayList<>();
        set.add(Integer.toString(charID));
        set.add(uuid);
        insertToDB("UPDATE characterHolder SET charSlot" + slotID + " = ? WHERE uuid = ?", set);
    }

    public void setStorageSlots() {
        storageSlots = this.getStorageSlots();
    }

    public void setStorage(int charID, int slotID) {
        ArrayList<String> set = new ArrayList<>();
        set.add(Integer.toString(charID));
        set.add(uuid);
        insertToDB("UPDATE characterHolder SET storageSlot" + slotID + " = ? WHERE uuid = ?", set);
    }

    public void setActive(int charID) {
        ArrayList<String> vals = new ArrayList<>();
        vals.add(Integer.toString(charID));
        vals.add(uuid);
        insertToDB("UPDATE characterHolder SET active = ? WHERE uuid = ?", vals);
        active = this.getActive();
    }


}
