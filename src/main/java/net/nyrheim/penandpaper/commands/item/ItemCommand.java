package net.nyrheim.penandpaper.commands.item;

import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (!sender.hasPermission("penandpaper.command.item")) {
            sender.sendMessage(RED + "You do not have permission to create items.");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        if (args.length <= 0) {
            sender.sendMessage(RED + "Usage: /" + label + " [item] (amount)");
            return true;
        }
        int amount = 1;
        boolean amountSpecified = false;
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[args.length - 1]);
                amountSpecified = true;
            } catch (NumberFormatException ignored) {}
        }
        String typeName = Arrays.stream(args)
                .limit(amountSpecified ? (args.length - 1) : args.length)
                .reduce((a, b) -> a + "_" + b)
                .orElse("")
                .toUpperCase();
        ItemType type;
        try {
            type = ItemType.valueOf(typeName);
        } catch (IllegalArgumentException exception) {
            sender.sendMessage(RED + "No item by that name (" + typeName + ") found. (n.b. only D&D items are accepted, not Minecraft items!)");
            return true;
        }
        PenItemStack penItemStack = new PenItemStack(type, amount);
        Player player = (Player) sender;
        player.getInventory().addItem(penItemStack.toItemStack());
        sender.sendMessage(GREEN + "Created " + amount + " \u00d7 " + type.getName());
        return true;
    }
}
