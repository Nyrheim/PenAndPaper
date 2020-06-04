package net.nyrheim.penandpaper;

import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.commands.ability.AbilityCommand;
import net.nyrheim.penandpaper.commands.character.CharacterCommand;
import net.nyrheim.penandpaper.commands.clazz.ClassCommand;
import net.nyrheim.penandpaper.commands.exp.ExperienceCommand;
import net.nyrheim.penandpaper.commands.levelup.LevelUpCommand;
import net.nyrheim.penandpaper.commands.soul.SoulCommand;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.listener.InventoryClickListener;
import net.nyrheim.penandpaper.listener.PlayerListener;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.race.PenRaceService;
import net.nyrheim.penandpaper.rpkit.character.PenRPKCharacterProvider;
import net.nyrheim.penandpaper.rpkit.clazz.PenRPKClassProvider;
import net.nyrheim.penandpaper.rpkit.economy.PenRPKCurrencyProvider;
import net.nyrheim.penandpaper.rpkit.economy.PenRPKEconomyProvider;
import net.nyrheim.penandpaper.rpkit.experience.PenRPKExperienceProvider;
import net.nyrheim.penandpaper.rpkit.player.PenRPKPlayerProvider;
import net.nyrheim.penandpaper.rpkit.profile.PenRPKMinecraftProfileProvider;
import net.nyrheim.penandpaper.rpkit.profile.PenRPKProfileProvider;
import net.nyrheim.penandpaper.rpkit.race.PenRPKRaceProvider;
import net.nyrheim.penandpaper.rpkit.stat.PenRPKStatProvider;
import net.nyrheim.penandpaper.service.Services;
import com.rpkit.core.bukkit.event.provider.RPKBukkitServiceProviderReadyEvent;
import com.rpkit.core.bukkit.plugin.RPKBukkitPlugin;
import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.core.service.ServiceProvider;
import com.rpkit.languages.bukkit.language.RPKLanguageProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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

public class PenAndPaper extends RPKBukkitPlugin implements Listener {

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
        services.register(PenRaceService.class, new PenRaceService(this));

        setServiceProviders(new ServiceProvider[] {
                new PenRPKCharacterProvider(this),
                new PenRPKClassProvider(this),
                new PenRPKCurrencyProvider(),
                new PenRPKEconomyProvider(),
                new PenRPKExperienceProvider(this),
                new PenRPKPlayerProvider(this),
                new PenRPKMinecraftProfileProvider(this),
                new PenRPKProfileProvider(this),
                new PenRPKRaceProvider(this),
                new PenRPKStatProvider()
        });
    }

    private boolean racesInitialized = false;

    @Override
    public void onPostEnable() {
        if (racesInitialized) return;
        attemptRaceInitialization();
    }

    @EventHandler
    public void onServiceReady(RPKBukkitServiceProviderReadyEvent event) {
        if (racesInitialized) return;
        attemptRaceInitialization();
    }

    private void attemptRaceInitialization() {
        try {
            core.getServiceManager().getServiceProvider(RPKLanguageProvider.class);
            getServices().get(PenRaceService.class).initializeRaces();
            racesInitialized = true;
        } catch (UnregisteredServiceException ignored) {}
    }

    @Override
    public void registerListeners() {
        registerListeners(
                new InventoryClickListener(this),
                new PlayerListener(),
                this
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
