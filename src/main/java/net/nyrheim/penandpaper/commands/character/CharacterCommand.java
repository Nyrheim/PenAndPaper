package net.nyrheim.penandpaper.commands.character;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static org.bukkit.ChatColor.*;

public final class CharacterCommand implements CommandExecutor {

    private final PenAndPaper plugin;
    private final CharacterStatsCommand characterStatsCommand;
    private final CharacterSetCommand characterSetCommand;
    private final CharacterAddCommand characterAddCommand;
    private final CharacterDeleteCommand characterDeleteCommand;

    public CharacterCommand(PenAndPaper plugin) {
        this.plugin = plugin;
        this.characterStatsCommand = new CharacterStatsCommand(plugin);
        this.characterSetCommand = new CharacterSetCommand(plugin);
        this.characterAddCommand = new CharacterAddCommand(plugin);
        this.characterDeleteCommand = new CharacterDeleteCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;
        if (sender instanceof Player) {
            target = (Player) sender;
        }
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "stats":
                    return characterStatsCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "set":
                    return characterSetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "add":
                    return characterAddCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "delete":
                    return characterDeleteCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    target = plugin.getServer().getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(RED + "There is no player online by that name.");
                        return true;
                    }
                    break;
            }
        }
        if (target == null) {
            sender.sendMessage(RED + "You must specify a player when running this command from console.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return true;
        }
        TextComponent name = new TextComponent(GOLD + character.getName());
        name.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/char set name"));
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to change your character's name!").create()));
        sender.spigot().sendMessage(name);
        TextComponent age = new TextComponent(AQUA + "Age: " + WHITE + (character.getAge() < 0 ? "Empty" : character.getAge()));
        age.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/char set age"));
        age.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to change your character's age!").create()));
        sender.spigot().sendMessage(age);
        sender.sendMessage(AQUA + "Race: " + WHITE + (character.getRace() == null ? "Empty" : character.getRace().getName()));
        TextComponent height = new TextComponent(AQUA + "Height: " + WHITE + (character.getHeight().isEmpty() ? "Empty" : character.getHeight()));
        height.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/char set height"));
        height.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to change your character's height!").create()));
        sender.spigot().sendMessage(height);
        TextComponent weight = new TextComponent(AQUA + "Weight: " + WHITE + (character.getWeight().isEmpty() ? "Empty" : character.getWeight()));
        weight.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/char set weight"));
        weight.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to change your character's weight!").create()));
        sender.spigot().sendMessage(weight);
        TextComponent appearance = new TextComponent(AQUA + "Appearance: " + WHITE + (character.getAppearance().isEmpty() ? "Empty" : character.getAppearance()));
        appearance.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/char set appearance"));
        appearance.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to change your character's appearance!").create()));
        sender.spigot().sendMessage(appearance);
        TextComponent presence = new TextComponent(AQUA + "Presence: " + WHITE + (character.getPresence().isEmpty() ? "Empty" : character.getPresence()));
        presence.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/char set presence"));
        presence.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to change your character's presence!").create()));
        sender.spigot().sendMessage(presence);
        sender.sendMessage(AQUA + "Player: " + WHITE + target.getName());
        return true;
    }
}
