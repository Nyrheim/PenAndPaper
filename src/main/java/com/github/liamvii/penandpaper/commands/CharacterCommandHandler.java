package com.github.liamvii.penandpaper.commands;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import com.github.liamvii.penandpaper.gui.HolderGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;

public class CharacterCommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();
        CharacterHolder holder = Pen.getHolder(player);
        PlayerCharacter character = Pen.getCharacter(holder.getActive());
        if (!(sender instanceof Player)) {
            getLogger().info("WARN: This command may only be executed by players.");
            return false;
        }
        else if (args.length == 0) {
            openSoulGUI(player);
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                characterHelp(sender);
            }
            else if (args[0].equalsIgnoreCase(("stats"))) {
                sendStats(player, player);
            }
            else if (Bukkit.getPlayerExact(args[0]) != null) {
                Player target = Bukkit.getPlayerExact(args[0]);
                sendCharacterCard(player, target);
                return true;
                }
            }
        else if (args.length > 2) {
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("age")) {
                    if (args[2].matches("\\d+")) {
                        character.setAge(Integer.parseInt(args[2]));
                        player.sendMessage(ChatColor.GREEN + "Age set to " + character.getAge());
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "You may only set your age to a number.");
                        return true;
                    }
                }
                if (args[1].equalsIgnoreCase("height")) {
                    String concatHeight = args[2];
                    for (int len = 3; len < args.length; len++) {
                        concatHeight = concatHeight + " " + args[len];
                    }
                    character.setHeight(concatHeight);
                    return true;
                }
                if (args[1].equalsIgnoreCase("weight")) {
                    String concatWeight = args[2];
                    for (int len = 3; len < args.length; len++) {
                        concatWeight = concatWeight + " " + args[len];
                    }
                    character.setWeight(concatWeight);
                    return true;
                }
                if (args[1].equalsIgnoreCase("appearance")) {
                    String concatApp = args[2];
                    for (int len = 3; len < args.length; len++) {
                        concatApp = concatApp + " " + args[len];
                    }
                    character.setAppearance(concatApp);
                    return true;
                }
                if (args[1].equalsIgnoreCase("presence")) {
                    String concatPres = args[2];
                    for (int len = 3; len < args.length; len++) {
                        concatPres = concatPres + " " + args[len];
                    }
                    character.setPresence(concatPres);
                    return true;
                }
                if (Bukkit.getPlayerExact(args[1]) != null) {
                    if (args[2].equalsIgnoreCase("exp")) {
                        if (player.hasPermission("penandpaper.admin")) {
                            Player target = Bukkit.getPlayerExact(args[0]);
                            CharacterHolder holderT = Pen.getHolder(target);
                            PlayerCharacter characterT = Pen.getCharacter(holder.getActive());
                            if (args[3].matches("\\d+")) {
                                characterT.setEXP(Integer.parseInt(args[3]));
                            }
                            else {
                                sender.sendMessage(ChatColor.RED + "Please enter a number!");
                            }
                        }
                    }
                }
            }
            if (args[0].equalsIgnoreCase("add")) {

                if (args[1].equalsIgnoreCase("appearance")) {
                    String origApp = character.getAppearance();
                    String concatApp = args[2];
                    for (int len = 3; len < args.length; len++) {
                        concatApp = concatApp + " " + args[len];
                    }
                    character.setAppearance(origApp + " " + concatApp);
                    return true;
                }
                if (args[1].equalsIgnoreCase("presence")) {
                    String origPres = character.getAppearance();
                    String concatPres = args[2];
                    for (int len = 3; len < args.length; len++) {
                        concatPres = concatPres + " " + args[len];
                    }
                    character.setAppearance(origPres + " " + concatPres);
                    return true;
                }
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "Invalid command. Please refer to /soul help for a list of valid commands.");
        }
        return true;
    }

    private boolean characterHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "Pen & Paper:" + ChatColor.AQUA + " Soul Commands");
        sender.sendMessage(ChatColor.AQUA + "/soul " + ChatColor.WHITE +  "- Opens the soul menu.");
        sender.sendMessage(ChatColor.AQUA + "/soul set age " + ChatColor.WHITE + "- Sets your active character's age.");
        return true;
    }

    private void openSoulGUI(Player player) {
        HolderGUI gui = new HolderGUI();
        gui.initializeItems(player);
        gui.openInventory(player);

    }

    public void sendStats(Player sender, Player target) {
        CharacterHolder holder = Pen.getHolder(target);
        PlayerCharacter character = Pen.getCharacter(holder.getActive());
        HashMap<String, Integer> scores = character.getAbilityScores();
        HashMap<String, Integer> modifiers = character.getModifiers();
        ArrayList<Integer> jobIDs = character.getJobIDs();
        sender.sendMessage(ChatColor.GOLD + target.getName() + "'s Character Stats");
        if (jobIDs.size() < 2) {
            sender.sendMessage(ChatColor.GOLD + Pen.getJob(jobIDs.get(0)).getJobName() + " " + Pen.getJob(jobIDs.get(0)).getJobLevel());
        }
        else if (jobIDs.size() == 2) {
            sender.sendMessage(ChatColor.GOLD + Pen.getJob(jobIDs.get(1)).getJobName() + " " + Pen.getJob(jobIDs.get(1)).getJobLevel() + "/" + Pen.getJob(jobIDs.get(2)).getJobName() + " " + Pen.getJob(jobIDs.get(2)).getJobLevel());
        }
        sender.sendMessage(ChatColor.AQUA + "Level: " + ChatColor.WHITE + character.getLevel());
        sender.sendMessage(ChatColor.GOLD + "STR: " + ChatColor.WHITE + scores.get("STR") + " (+" + modifiers.get("STR") + ")");
        sender.sendMessage(ChatColor.GOLD + "DEX: " + ChatColor.WHITE + scores.get("DEX") + " (+" + modifiers.get("DEX") + ")");
        sender.sendMessage(ChatColor.GOLD + "CON: " + ChatColor.WHITE + scores.get("CON") + " (+" + modifiers.get("CON") + ")");
        sender.sendMessage(ChatColor.GOLD + "INT: " + ChatColor.WHITE + scores.get("INT") + " (+" + modifiers.get("INT") + ")");
        sender.sendMessage(ChatColor.GOLD + "WIS: " + ChatColor.WHITE + scores.get("WIS") + " (+" + modifiers.get("WIS") + ")");
        sender.sendMessage(ChatColor.GOLD + "CHA: " + ChatColor.WHITE + scores.get("CHA") + " (+" + modifiers.get("CHA") + ")");
    }

    public void sendCharacterCard(Player sender, Player target) {
        CharacterHolder holder = Pen.getHolder(target);
        PlayerCharacter character = Pen.getCharacter(holder.getActive());
        if (character.getFirstName() == null) {
            sender.sendMessage(ChatColor.RED + "This player does not have a character!");
        }
        sender.sendMessage(ChatColor.GOLD + target.getName() + "'s Character Card:");
        sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE + character.getFirstName() + " " + character.getFamilyName());
        if (character.getAge() == -1) {
            sender.sendMessage(ChatColor.AQUA + "Age: " + ChatColor.WHITE + "Empty.");
        }
        else {
            sender.sendMessage(ChatColor.AQUA + "Age: " + ChatColor.WHITE + character.getAge());
        }
        if (character.getHeight() == "-1") {
            sender.sendMessage(ChatColor.AQUA + "Height: " + ChatColor.WHITE + "Empty.");
        }
        else {
            sender.sendMessage(ChatColor.AQUA + "Height: " + ChatColor.WHITE + character.getHeight());
        }
        if (character.getWeight() == "-1") {
            sender.sendMessage(ChatColor.AQUA + "Weight: " + ChatColor.WHITE + "Empty.");
        }
        else {
            sender.sendMessage(ChatColor.AQUA + "Weight: " + ChatColor.WHITE + character.getWeight());
        }
        if (character.getAppearance() == " ") {
            sender.sendMessage(ChatColor.AQUA + "Appearance: " + ChatColor.WHITE + "Empty.");
        }
        else {
            sender.sendMessage(ChatColor.AQUA + "Appearance: " + ChatColor.WHITE + character.getAppearance());
        }
        if (character.getPresence() == " ") {
            sender.sendMessage(ChatColor.AQUA + "Presence: " + ChatColor.WHITE + "Empty.");
        }
        else {
            sender.sendMessage(ChatColor.AQUA + "Presence: " + ChatColor.WHITE + character.getPresence());
        }
    }
}
