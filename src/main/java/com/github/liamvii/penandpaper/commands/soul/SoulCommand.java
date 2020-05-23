package com.github.liamvii.penandpaper.commands.soul;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.gui.SoulGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.RED;

public final class SoulCommand implements CommandExecutor {

    private final Pen plugin;

    public SoulCommand(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        SoulGUI gui = new SoulGUI(plugin);
        gui.initializeItems(player);
        gui.openInventory(player);
        return true;
    }

}
