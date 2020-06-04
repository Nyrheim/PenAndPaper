package net.nyrheim.penandpaper.conversations;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class FamilyNameSelect extends StringPrompt {

    @Override
    public String getPromptText(ConversationContext context) {
        Player player = (Player) context.getForWhom();
        return ChatColor.GREEN + "What is your character's family name?";
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String answer) {
        Player player = ((Player) context.getForWhom());
        player.sendRawMessage(ChatColor.GOLD + "Character's family name set to: " + ChatColor.WHITE + answer);
        PenAndPaper.addAnswer(player, answer);
        return new AbilitySelect();
    }
}
