package com.github.liamvii.penandpaper.conversations;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.gui.JobGUI;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static com.github.liamvii.penandpaper.utils.Insert.insertToDB;

public class AbilitySelect extends ValidatingPrompt {

    private Pen plugin;
// TODO: Incorrect ability score input is crashing the server.
    @Override
    protected boolean isInputValid(ConversationContext context, String answer) {
        Player player = (Player) context.getForWhom();
        String uuid = player.getUniqueId().toString();
        final int TOTAL_POINTS = 27;
        int usedPoints = 0;
        final int[] SCORE_VALUES = {8, 9, 10, 11, 12, 13, 14, 15};
        final int[] POINT_COST = {0, 1, 2, 3, 4, 5, 7, 9};
        int[] scores = new int[6];
        String[] abilityScores = new String[6];
        answer.replaceAll("[ ;]", "");
        abilityScores = answer.trim().split("\\s*,");
        if (abilityScores.length == 6) {
            for (int i = 0; i < abilityScores.length; i++) {
                scores[i] = Integer.parseInt(abilityScores[i].trim());
            }
            System.out.println(Arrays.toString(scores));
                for (int scoreIterate = 0; scoreIterate < 6; scoreIterate++) {
                    if (scores[scoreIterate] > 15 || scores[scoreIterate] < 8) {
                        return false;
                    }
                    else {
                        for (int valuesIterate = 0; valuesIterate < 8; valuesIterate++) {
                            if (scores[scoreIterate] == SCORE_VALUES[valuesIterate]) {
                                usedPoints = usedPoints + POINT_COST[valuesIterate];
                            }
                        }
                    }
                }
                if (usedPoints > TOTAL_POINTS) {
                    return false;
                }
            player.sendRawMessage(ChatColor.GOLD + "Ability scores set to: ");
            player.sendRawMessage(ChatColor.GOLD + "STR: " + ChatColor.WHITE + scores[0]);
            player.sendRawMessage(ChatColor.GOLD + "DEX: " + ChatColor.WHITE + scores[1]);
            player.sendRawMessage(ChatColor.GOLD + "CON: " + ChatColor.WHITE + scores[2]);
            player.sendRawMessage(ChatColor.GOLD + "INT: " + ChatColor.WHITE + scores[3]);
            player.sendRawMessage(ChatColor.GOLD + "WIS: " + ChatColor.WHITE + scores[4]);
            player.sendRawMessage(ChatColor.GOLD + "CHA: " + ChatColor.WHITE + scores[5]);
            PlayerCharacter character = Pen.getCharacter(Pen.getHolder(player).getActive());
            character.setFreshAbilityScores(scores);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String getFailedValidationText(ConversationContext context, String answer) {
        return ChatColor.RED + "Please ensure you are entering your scores in the format described, using a 27 point buy calculator to calculate your scores.";
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String answer) {
        Player player = (Player) context.getForWhom();
        LinkedList<String> list = Pen.getAnswers(player);
        ArrayList<String> vals = new ArrayList<>();
        vals.add(list.get(0));
        vals.add(list.get(1));
        vals.add("-1");
        vals.add("-1");
        vals.add("-1");
        vals.add("");
        vals.add("");
        vals.add(Integer.toString(Pen.getHolder(player).getActive()));
        insertToDB("UPDATE characters SET firstName = ?, familyName = ?, age = ?, height = ?, weight = ?, appearance = ?, presence = ? WHERE id = ?", vals);
        JobGUI createJobGUI = new JobGUI();
        createJobGUI.initializeItems();
        createJobGUI.openInventory(player);
        return END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return ChatColor.GREEN + "Please enter your ability score array, following the point buy system, in the order: " + ChatColor.WHITE + "" +
                "STR, DEX, CON, INT, WIS, CHA." + ChatColor.GREEN + " Do " + ChatColor.RED + "NOT" + ChatColor.GREEN + " include any racial modifiers. You should separate each individual score with a comma. For example:" + ChatColor.WHITE +
                " 12, 12, 14, 12, 12, 12";
    }
}