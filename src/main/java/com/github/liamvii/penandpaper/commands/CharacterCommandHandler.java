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
        return true;
    }
}
