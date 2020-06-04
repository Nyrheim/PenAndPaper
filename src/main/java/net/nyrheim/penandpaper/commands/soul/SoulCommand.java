package net.nyrheim.penandpaper.commands.soul;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.gui.SoulGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.RED;

public final class SoulCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public SoulCommand(PenAndPaper plugin) {
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
