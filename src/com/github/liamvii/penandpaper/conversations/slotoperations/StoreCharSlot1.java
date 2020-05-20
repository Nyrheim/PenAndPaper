package com.github.liamvii.penandpaper.conversations.slotoperations;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;

public class StoreCharSlot1 extends ValidatingPrompt {

    @Override
    protected boolean isInputValid(ConversationContext conversationContext, String s) {
        Player player = (Player) conversationContext.getForWhom();
        CharacterHolder holder = Pen.getHolder(player);
        if (s.equalsIgnoreCase("y")) {
            PlayerCharacter character = Pen.getCharacter(holder.getChar(1));
            character.delChar(1, holder);
            player.sendRawMessage(ChatColor.RED + "Character stored.");
            return true;
        }
        if (s.equalsIgnoreCase("n")) {
            return true;
        }
        return false;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, String s) {
        return END_OF_CONVERSATION;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return ChatColor.RED + "Are you sure you would like to store this character? Type Y/N.";
    }

}
