package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class CharacterDeleteCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public CharacterDeleteCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify a character ID.");
            return true;
        }
        int characterIdInt;
        try {
            characterIdInt = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Character ID must be an integer.");
            return true;
        }
        CharacterId characterId = new CharacterId(characterIdInt);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getCharacter(characterId);
        if (character == null) {
            sender.sendMessage(RED + "There is no character by that ID.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(character.getPlayerId());
        if (!penPlayer.getPlayerUUID().getValue().equals(player.getUniqueId())) {
            sender.sendMessage(RED + "You do not own that character.");
            return true;
        }
        PenCharacter activeCharacter = characterService.getActiveCharacter(penPlayer);
        if (activeCharacter != null) {
            if (characterId.equals(activeCharacter.getId())) {
                characterService.setActiveCharacter(penPlayer, null);
            }
        }
        characterService.deleteCharacter(character);
        sender.sendMessage(GREEN + character.getName() + " successfully deleted.");
        return true;
    }
}
