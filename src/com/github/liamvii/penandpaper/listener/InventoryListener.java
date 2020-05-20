package com.github.liamvii.penandpaper.listener;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.character.jobs.Job;
import com.github.liamvii.penandpaper.character.jobs.JobFactory;
import com.github.liamvii.penandpaper.character.races.Race;
import com.github.liamvii.penandpaper.character.races.RaceFactory;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import com.github.liamvii.penandpaper.conversations.slotoperations.DelCharSlot1;
import com.github.liamvii.penandpaper.conversations.slotoperations.DelCharSlot2;
import com.github.liamvii.penandpaper.conversations.slotoperations.DelCharSlot3;
import com.github.liamvii.penandpaper.gui.HolderGUI;
import com.github.liamvii.penandpaper.gui.JobGUI;
import com.github.liamvii.penandpaper.gui.LevelGUI;
import com.github.liamvii.penandpaper.gui.RaceGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    private Pen plugin;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        CharacterHolder holder = Pen.getHolder(player);
        if (!(e.getInventory().getHolder() instanceof JobGUI || e.getInventory().getHolder() instanceof HolderGUI || e.getInventory().getHolder() instanceof RaceGUI )) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }
        e.setCancelled(true);
        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.PAPER) return;

        if (e.getInventory().getHolder() instanceof JobGUI) {
            JobFactory jobFactory = new JobFactory(player);
            Job selectedJob = jobFactory.getObject(e.getRawSlot(), player);
            selectedJob.freshSerialize(player);
            PlayerCharacter character = new PlayerCharacter(player);
            Pen.addCharacter(Pen.getHolder(player).getActive(), character);
            player.closeInventory();
            RaceGUI createRaceGUI = new RaceGUI();
            createRaceGUI.initializeItems();
            createRaceGUI.openInventory(player);
        }
        if (e.getInventory().getHolder() instanceof RaceGUI) {
            RaceFactory raceFactory = new RaceFactory(player);
            Race selectedRace = raceFactory.getObject(e.getRawSlot(), player);
            selectedRace.serialize(player);
            player.closeInventory();

        }
        if (e.getInventory().getHolder() instanceof LevelGUI) {
            PlayerCharacter character = Pen.getCharacter(holder.getActive());
            Job job = Pen.getJob(character.getJobID(1));
            if(e.getRawSlot() == 3) {
                job.jobLevelUp();
                player.sendMessage(ChatColor.GREEN + "Applied level to " + job.getJobName() + ".");
                player.closeInventory();
            }
        }
        if (e.getInventory().getHolder() instanceof HolderGUI) {
            if(clickedItem.getType() == Material.WRITABLE_BOOK && e.getRawSlot() == 0) {
                PlayerCharacter character = new PlayerCharacter(1, player);
                player.closeInventory();
            }
            if (clickedItem.getType() == Material.PLAYER_HEAD && e.getRawSlot() == 0) {
                if (holder.getActive() == holder.getChar(1)) {
                    player.closeInventory();
                }
                else {
                    PlayerCharacter character = new PlayerCharacter(1, holder);
                    player.closeInventory();
                }
            }
            if(e.getRawSlot() == 9) {
                if (holder.getChar(1) != -1) {
                    player.closeInventory();
                    createDel1Conversation(player);
                }
            }
            if(e.getRawSlot() == 10) {
                if (holder.getChar(1) != -1) {
                    player.closeInventory();

                }
            }

        }

    }

    private void createDel1Conversation(Player player) {
        ConversationFactory cf = new ConversationFactory(plugin);
        Conversation conv = cf.withFirstPrompt(new DelCharSlot1()).withLocalEcho(false).buildConversation(player);
        conv.begin();
    }

    private void createDel2Conversation(Player player) {
        ConversationFactory cf = new ConversationFactory(plugin);
        Conversation conv = cf.withFirstPrompt(new DelCharSlot2()).withLocalEcho(false).buildConversation(player);
        conv.begin();
    }

    private void createDel3Conversation(Player player) {
        ConversationFactory cf = new ConversationFactory(plugin);
        Conversation conv = cf.withFirstPrompt(new DelCharSlot3()).withLocalEcho(false).buildConversation(player);
        conv.begin();
    }

}

