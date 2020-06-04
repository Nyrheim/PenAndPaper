package net.nyrheim.penandpaper.conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

public class StartCreate extends MessagePrompt {

    @Override
    protected Prompt getNextPrompt(ConversationContext conversationContext) {
        return new FirstNameSelect();
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return ChatColor.AQUA + "Welcome to Pen & Paper's Soul Creation menu.";
    }
}
