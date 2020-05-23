package com.github.liamvii.penandpaper.commands.exp;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.MAX_EXPERIENCE;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public class ExperienceAddCommand implements CommandExecutor {

    private final Pen plugin;

    public ExperienceAddCommand(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("penandpaper.command.exo.add")) {
            sender.sendMessage(RED + "You do not have permission.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify who to set the experience of.");
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(RED + "There is no player online by that name.");
            return true;
        }
        PlayerId playerId = new PlayerId(target);
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        CharacterId activeCharacterId = activeCharacterTable.get(playerId);
        if (activeCharacterId == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return true;
        }
        PlayerCharacter character = characterTable.get(activeCharacterId);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "You must specify how much experience to add.");
            return true;
        }
        int experience;
        try {
            experience = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Experience must be an integer.");
            return true;
        }
        if (character.getExperience() + experience > MAX_EXPERIENCE) {
            sender.sendMessage(RED + "You may not set experience values higher than " + MAX_EXPERIENCE);
            return true;
        }
        if (experience < 0) {
            sender.sendMessage(RED + "You may not add negative experience.");
            return true;
        }
        character.setExperience(character.getExperience() + experience);
        characterTable.update(character);
        sender.sendMessage(GREEN + character.getName() + "'s experience was set to " + character.getExperience() + ".");
        target.sendMessage(GREEN + "Your experience increased.");
        return true;
    }
}
