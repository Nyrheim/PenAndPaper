package com.github.liamvii.penandpaper.commands.ability;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.github.liamvii.penandpaper.ability.AbilityScoreCostLookupTable.MAX_ABILITY_COST;
import static com.github.liamvii.penandpaper.ability.AbilityScoreCostLookupTable.getAbilityScoreCost;
import static org.bukkit.ChatColor.RED;

public final class AbilityChoiceCommand implements CommandExecutor {

    private final Pen plugin;

    private final AbilityChoiceSetCommand abilityChoiceSetCommand;
    private final AbilityChoiceConfirmCommand abilityChoiceConfirmCommand;

    public AbilityChoiceCommand(Pen plugin) {
        this.plugin = plugin;
        this.abilityChoiceSetCommand = new AbilityChoiceSetCommand(plugin, this);
        this.abilityChoiceConfirmCommand = new AbilityChoiceConfirmCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "set":
                    return abilityChoiceSetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "confirm":
                    return abilityChoiceConfirmCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " choice [set|confirm]");
                    break;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(RED + "You must be a player to perform this command.");;
                return true;
            }
            Player player = (Player) sender;
            PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
            PenPlayer penPlayer = playerService.getPlayer(player);
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            PenCharacter character = characterService.getActiveCharacter(penPlayer);
            if (character == null) {
                sender.sendMessage(RED + "You do not currently have an active character.");
                return true;
            }
            if (Arrays.stream(Ability.values()).anyMatch(ability -> character.getAbilityScore(ability) > 0)) {
                sender.sendMessage(RED + "You have already chosen this character's stats. Please contact a member of staff for any modifications.");
                return true;
            }
            sendAbilityChoices(sender, character);
        }
        return true;
    }

    public void sendAbilityChoices(CommandSender sender, PenCharacter character) {
        for (Ability ability : Ability.values()) {
            ComponentBuilder abilityLineBuilder = new ComponentBuilder()
                    .append(ability.getAbbreviation())
                    .color(net.md_5.bungee.api.ChatColor.WHITE);
            int[] scores = { 8, 9, 10, 11, 12, 13, 14, 15 };
            Arrays.stream(scores).forEach(score -> {
                net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.GRAY;
                if (character.getAbilityScoreChoice(ability) == score) {
                    color = net.md_5.bungee.api.ChatColor.WHITE;
                } else {
                    int cost = getAbilityScoreCost(score);
                    int otherScoreCost = Arrays.stream(Ability.values())
                            .filter(otherAbility -> otherAbility != ability)
                            .map(otherAbility -> getAbilityScoreCost(character.getAbilityScoreChoice(otherAbility)))
                            .reduce(0, Integer::sum);
                    if (otherScoreCost + cost > MAX_ABILITY_COST) {
                        color = net.md_5.bungee.api.ChatColor.DARK_GRAY;
                    }
                }
                TextComponent scoreComponent = new TextComponent(Integer.toString(score));
                if (color == net.md_5.bungee.api.ChatColor.GRAY) {
                    scoreComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ability choice set " + ability.getAbbreviation() + " " + score));
                    scoreComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder().append("Click here to set your " + ability.getName() + " score to " + score + ".").create()));
                } else if (color == net.md_5.bungee.api.ChatColor.DARK_GRAY) {
                    scoreComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder().append("Decrease your other stats if you wish to choose this option.").create()));
                }
                abilityLineBuilder.append(" - ")
                        .color(net.md_5.bungee.api.ChatColor.WHITE)
                        .append(scoreComponent)
                        .color(color);
            });
            sender.spigot().sendMessage(abilityLineBuilder.create());
        }
        if (Arrays.stream(Ability.values())
                .allMatch(ability -> character.getAbilityScoreChoice(ability) >= 8
                        && character.getAbilityScoreChoice(ability) <= 15)) {
            TextComponent confirmButton = new TextComponent("Confirm");
            confirmButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ability choice confirm"));
            confirmButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder()
                            .append("Click here to lock in your ability score choices. ")
                            .color(net.md_5.bungee.api.ChatColor.WHITE)
                            .append("WARNING: PERMANENT!")
                            .color(net.md_5.bungee.api.ChatColor.RED)
                            .create()));
            confirmButton.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            sender.spigot().sendMessage(confirmButton);
        }
    }

}
