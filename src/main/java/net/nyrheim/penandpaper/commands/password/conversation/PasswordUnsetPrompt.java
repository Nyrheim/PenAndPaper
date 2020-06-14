package net.nyrheim.penandpaper.commands.password.conversation;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.ChatColor.GREEN;

public final class PasswordUnsetPrompt extends MessagePrompt {

    private final PenAndPaper plugin;

    public PasswordUnsetPrompt(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    protected @Nullable Prompt getNextPrompt(@NotNull ConversationContext context) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer((Player) context.getForWhom());
        penPlayer.setPassword((String) context.getSessionData("password"));
        playerService.updatePlayer(penPlayer);
        return END_OF_CONVERSATION;
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return GREEN + "Password unset, login disabled.";
    }
}
