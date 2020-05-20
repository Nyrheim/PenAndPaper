package com.github.liamvii.penandpaper.conversations.slotoperations;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;

public class DelCharSlot3 extends ValidatingPrompt {

    @Override
    protected boolean isInputValid(ConversationContext conversationContext, String s) {
        Player player = (Player) conversationContext.getForWhom();
        CharacterHolder holder = Pen.getHolder(player);
        if (s.equalsIgnoreCase("y")) {
            PlayerCharacter character = Pen.getCharacter(holder.getChar(3));
            character.delChar(3, holder);
            player.sendRawMessage(ChatColor.RED + "Character deleted.");
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
        return ChatColor.RED + "Are you sure you would like to delete this character? Type Y/N.";
    }

}
