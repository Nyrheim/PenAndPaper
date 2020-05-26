package com.github.liamvii.penandpaper;

import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.commands.ability.AbilityCommand;
import com.github.liamvii.penandpaper.commands.character.CharacterCommand;
import com.github.liamvii.penandpaper.commands.clazz.ClassCommand;
import com.github.liamvii.penandpaper.commands.exp.ExperienceCommand;
import com.github.liamvii.penandpaper.commands.levelup.LevelUpCommand;
import com.github.liamvii.penandpaper.commands.soul.SoulCommand;
import com.github.liamvii.penandpaper.database.Database;
import com.github.liamvii.penandpaper.listener.InventoryClickListener;
import com.github.liamvii.penandpaper.listener.PlayerListener;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import com.github.liamvii.penandpaper.rpkit.character.PenRPKCharacterProvider;
import com.github.liamvii.penandpaper.rpkit.clazz.PenRPKClassProvider;
import com.github.liamvii.penandpaper.rpkit.economy.PenRPKCurrencyProvider;
import com.github.liamvii.penandpaper.rpkit.economy.PenRPKEconomyProvider;
import com.github.liamvii.penandpaper.rpkit.experience.PenRPKExperienceProvider;
import com.github.liamvii.penandpaper.rpkit.player.PenRPKPlayerProvider;
import com.github.liamvii.penandpaper.rpkit.profile.PenRPKMinecraftProfileProvider;
import com.github.liamvii.penandpaper.rpkit.profile.PenRPKProfileProvider;
import com.github.liamvii.penandpaper.rpkit.stat.PenRPKStatProvider;
import com.github.liamvii.penandpaper.service.Services;
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
    private Services services;

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

        services = new Services(this);
        services.register(PenPlayerService.class, new PenPlayerService(this));
        services.register(PenCharacterService.class, new PenCharacterService(this));

        setServiceProviders(new ServiceProvider[] {
                new PenRPKCharacterProvider(this),
                new PenRPKClassProvider(this),
                new PenRPKCurrencyProvider(),
                new PenRPKEconomyProvider(),
                new PenRPKExperienceProvider(this),
                new PenRPKPlayerProvider(this),
                new PenRPKMinecraftProfileProvider(this),
                new PenRPKProfileProvider(this),
                new PenRPKStatProvider()
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

    public Services getServices() {
        return services;
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
