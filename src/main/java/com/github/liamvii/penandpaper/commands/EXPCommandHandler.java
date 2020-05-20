package com.github.liamvii.penandpaper.commands;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EXPCommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        CharacterHolder holder = Pen.getHolder(player);
        PlayerCharacter character = Pen.getCharacter(holder.getActive());
        if (args.length == 0) {
            player.sendMessage(ChatColor.GREEN + "Your EXP is " + character.getEXP());
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("add")) {
                if (Bukkit.getPlayerExact(args[1]) != null) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    CharacterHolder holderT = Pen.getHolder(target);
                    PlayerCharacter characterT = Pen.getCharacter(holderT.getActive());
                    if (args[2].matches("\\d+")) {
                        characterT.incrementEXP(Integer.parseInt(args[2]), target);
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }
        return false;
    }
}
