package net.nyrheim.penandpaper;

import com.rpkit.core.bukkit.event.provider.RPKBukkitServiceProviderReadyEvent;
import com.rpkit.core.bukkit.plugin.RPKBukkitPlugin;
import com.rpkit.core.exception.UnregisteredServiceException;
import com.rpkit.core.service.ServiceProvider;
import com.rpkit.languages.bukkit.language.RPKLanguageProvider;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.commands.ability.AbilityCommand;
import net.nyrheim.penandpaper.commands.character.CharacterCommand;
import net.nyrheim.penandpaper.commands.clazz.ClassCommand;
import net.nyrheim.penandpaper.commands.dhp.DHPCommand;
import net.nyrheim.penandpaper.commands.exhaustion.ExhaustionCommand;
import net.nyrheim.penandpaper.commands.exp.ExperienceCommand;
import net.nyrheim.penandpaper.commands.hp.HPCommand;
import net.nyrheim.penandpaper.commands.ihp.IHPCommand;
import net.nyrheim.penandpaper.commands.item.ItemCommand;
import net.nyrheim.penandpaper.commands.levelup.LevelUpCommand;
import net.nyrheim.penandpaper.commands.sethp.SetHPCommand;
import net.nyrheim.penandpaper.commands.soul.SoulCommand;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.exhaustion.ExhaustionTask;
import net.nyrheim.penandpaper.item.PenRecipeService;
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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* Pen and Paper's main class.
Please avoid declaring unnecessary instances of this class, and follow best practice wherever possible throughout.
API: Spigot 1.14.4.
30/04/2020
 */
public final class PenAndPaper extends RPKBukkitPlugin implements Listener {

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
        services.register(PenRecipeService.class, new PenRecipeService(this));

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

        startExhaustionTask();
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
        getCommand("item").setExecutor(new ItemCommand());
        getCommand("hp").setExecutor(new HPCommand(this));
        getCommand("dhp").setExecutor(new DHPCommand(this));
        getCommand("ihp").setExecutor(new IHPCommand(this));
        getCommand("sethp").setExecutor(new SetHPCommand(this));
        getCommand("exhaustion").setExecutor(new ExhaustionCommand(this));
    }

    public Database getDatabase() {
        return database;
    }

    public Services getServices() {
        return services;
    }

    private void startExhaustionTask() {
        // Run first 30min after server starts, then every 60min after
        // If the server restarts a lot this means the delay after it's finished restarting will be 30min,
        // but if it's running constantly 30min is going to be about the average time it _could_ have been.
        // We could be smarter than this and actually store the last time it ran, and start it back up with the
        // correct delay each time if we wanted.
        new ExhaustionTask(this).runTaskTimer(this, 36000L, 72000L);
    }

}
