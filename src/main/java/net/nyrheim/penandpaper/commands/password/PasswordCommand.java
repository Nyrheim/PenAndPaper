package net.nyrheim.penandpaper.commands.password;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.commands.password.conversation.PasswordPrompt;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.RED;

public final class PasswordCommand implements CommandExecutor {

    private final ConversationFactory conversationFactory;

    public PasswordCommand(PenAndPaper plugin) {
        this.conversationFactory = new ConversationFactory(plugin)
                .withModality(true)
                .thatExcludesNonPlayersWithMessage(RED + "You must be a player to perform this command.")
                .withFirstPrompt(new PasswordPrompt(plugin))
                .withEscapeSequence("cancel")
                .addConversationAbandonedListener(event -> {
                    if (!event.gracefulExit()) {
                        if (event.getContext().getForWhom() instanceof CommandSender) {
                            CommandSender whomst = (CommandSender) event.getContext().getForWhom();
                            whomst.sendMessage(RED + "Operation cancelled.");
                        }
                    }
                });
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!(sender instanceof Conversable)) {
            sender.sendMessage(RED + "You cannot be conversed with (??)");
            return true;
        }
        Conversable conversable = (Conversable) sender;
        conversationFactory.buildConversation(conversable).begin();
        return true;
    }
}
