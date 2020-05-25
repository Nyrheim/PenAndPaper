package com.github.liamvii.penandpaper;

import com.github.liamvii.penandpaper.commands.ability.AbilityCommand;
import com.github.liamvii.penandpaper.commands.character.CharacterCommand;
import com.github.liamvii.penandpaper.commands.clazz.ClassCommand;
import com.github.liamvii.penandpaper.commands.exp.ExperienceCommand;
import com.github.liamvii.penandpaper.commands.levelup.LevelUpCommand;
import com.github.liamvii.penandpaper.commands.soul.SoulCommand;
import com.github.liamvii.penandpaper.database.Database;
import com.github.liamvii.penandpaper.listener.InventoryClickListener;
import com.github.liamvii.penandpaper.listener.PlayerListener;
import com.github.liamvii.penandpaper.rpkit.character.PnPCharacterProvider;
import com.github.liamvii.penandpaper.rpkit.clazz.PnPClassProvider;
import com.github.liamvii.penandpaper.rpkit.economy.PnPCurrencyProvider;
import com.github.liamvii.penandpaper.rpkit.economy.PnPEconomyProvider;
import com.github.liamvii.penandpaper.rpkit.experience.PnPExperienceProvider;
import com.github.liamvii.penandpaper.rpkit.player.PnPPlayerProvider;
import com.github.liamvii.penandpaper.rpkit.profile.PnPMinecraftProfileProvider;
import com.github.liamvii.penandpaper.rpkit.profile.PnPProfileProvider;
import com.github.liamvii.penandpaper.rpkit.stat.PnPStatProvider;
import com.rpkit.core.bukkit.plugin.RPKBukkitPlugin;
import com.rpkit.core.service.ServiceProvider;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* Pen and Paper's main class.
Please avoid declaring unnecessary instances of this class, and follow best practice wherever possible throughout.
API: Spigot 1.14.4.
30/04/2020
 */

// Empty slots (charSlot, jobID, etc.) are denoted by a value of -1 to make querying the database easier.
// The default 'Active' character for a player is -2, denoting no active character. This largely applies to new players, or those
// who have deleted their only character.

public class Pen extends RPKBukkitPlugin {

    private Database database;

    public static Map<Player, LinkedList<String>> answers = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        database = new Database(
                this,
                getConfig().getString("database.url"),
                getConfig().getString("database.username"),
                getConfig().getString("database.password")
        );

        setServiceProviders(new ServiceProvider[] {
                new PnPCharacterProvider(this),
                new PnPClassProvider(this),
                new PnPCurrencyProvider(),
                new PnPEconomyProvider(),
                new PnPExperienceProvider(this),
                new PnPPlayerProvider(this),
                new PnPMinecraftProfileProvider(this),
                new PnPProfileProvider(this),
                new PnPStatProvider()
        });
    }

    @Override
    public void registerListeners() {
        registerListeners(
                new InventoryClickListener(this),
                new PlayerListener()
        );
    }

    @Override
    public void registerCommands() {
        getCommand("character").setExecutor(new CharacterCommand(this));
        getCommand("experience").setExecutor(new ExperienceCommand(this));
        getCommand("soul").setExecutor(new SoulCommand(this));
        getCommand("levelup").setExecutor(new LevelUpCommand(this));
        getCommand("ability").setExecutor(new AbilityCommand(this));
        getCommand("class").setExecutor(new ClassCommand(this));
    }

    public Database getDatabase() {
        return database;
    }

    public static void addAnswer(Player player, String answer) {
        if (answers.containsKey(player)) {
            answers.get(player).add(answer);
        } else {
            answers.put(player, new LinkedList<>(Collections.singleton(answer)));
        }
    }

    public static LinkedList<String> getAnswers(Player player) {
        return answers.get(player);
    }


}
