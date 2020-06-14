package net.nyrheim.penandpaper.commands.password.conversation;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PasswordPrompt extends StringPrompt {

    private final PenAndPaper plugin;

    public PasswordPrompt(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {
        return "Enter the password you would like to set, or type \"cancel\" to cancel, or \"unset\" to unset your password.";
    }

    @Override
    public @NotNull Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        if (input == null || input.equals("unset")) {
            return new PasswordUnsetPrompt(plugin);
        }
        context.setSessionData("password", input);
        return new PasswordSetPrompt(plugin);
    }
}
