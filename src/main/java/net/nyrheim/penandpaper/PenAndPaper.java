package net.nyrheim.penandpaper;

import com.rpkit.core.bukkit.plugin.RPKBukkitPlugin;
import com.rpkit.core.service.ServiceProvider;
import net.iso2013.mlapi.api.MultiLineAPI;
import net.nyrheim.penandpaper.character.ITagController;
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
import net.nyrheim.penandpaper.commands.password.PasswordCommand;
import net.nyrheim.penandpaper.commands.roll.RollCommand;
import net.nyrheim.penandpaper.commands.sethp.SetHPCommand;
import net.nyrheim.penandpaper.commands.soul.SoulCommand;
import net.nyrheim.penandpaper.commands.temphp.TempHPCommand;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.exhaustion.ExhaustionTask;
import net.nyrheim.penandpaper.hunger.HungerFoodLevelChangeListener;
import net.nyrheim.penandpaper.hunger.HungerPlayerInteractListener;
import net.nyrheim.penandpaper.listener.InventoryClickListener;
import net.nyrheim.penandpaper.listener.PlayerInteractEntity;
import net.nyrheim.penandpaper.listener.PlayerListener;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.race.PenRaceService;
import net.nyrheim.penandpaper.rpkit.character.PenRPKCharacterProvider;
import net.nyrheim.penandpaper.rpkit.clazz.PenRPKClassProvider;
import net.nyrheim.penandpaper.rpkit.experience.PenRPKExperienceProvider;
import net.nyrheim.penandpaper.rpkit.player.PenRPKPlayerProvider;
import net.nyrheim.penandpaper.rpkit.profile.PenRPKMinecraftProfileProvider;
import net.nyrheim.penandpaper.rpkit.profile.PenRPKProfileProvider;
import net.nyrheim.penandpaper.rpkit.race.PenRPKRaceProvider;
import net.nyrheim.penandpaper.rpkit.stat.PenRPKStatProvider;
import net.nyrheim.penandpaper.service.Services;
import org.bukkit.Bukkit;

public class PenAndPaper extends RPKBukkitPlugin {

    private Database database;
    private Services services;
    private MultiLineAPI lineAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        lineAPI = (MultiLineAPI) Bukkit.getPluginManager().getPlugin("MultiLineAPI");
        if (lineAPI == null) {
            throw new IllegalStateException("Failed to start demo plugin! MultiLineAPI could not be found!");
        }
        lineAPI.addDefaultTagController(new ITagController(this));

        database = new Database(
                this,
                getConfig().getString("database.url"),
                getConfig().getString("database.username"),
                getConfig().getString("database.password")
        );

        services = new Services(this);
        services.register(PenPlayerService.class, new PenPlayerService(this));
        services.register(PenCharacterService.class, new PenCharacterService(this));
        services.register(PenRaceService.class, new PenRaceService());

        setServiceProviders(new ServiceProvider[] {
                new PenRPKCharacterProvider(this),
                new PenRPKClassProvider(this),
                new PenRPKExperienceProvider(this),
                new PenRPKPlayerProvider(this),
                new PenRPKMinecraftProfileProvider(this),
                new PenRPKProfileProvider(this),
                new PenRPKRaceProvider(this),
                new PenRPKStatProvider()
        });

        startExhaustionTask();
    }

    @Override
    public void registerListeners() {
        registerListeners(
                new InventoryClickListener(this),
                new PlayerListener(this),
                new PlayerInteractEntity(),
                new HungerFoodLevelChangeListener(),
                new HungerPlayerInteractListener()
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
        getCommand("roll").setExecutor(new RollCommand(this));
        getCommand("temphp").setExecutor(new TempHPCommand(this));
        getCommand("password").setExecutor(new PasswordCommand(this));
    }

    public Database getDatabase() {
        return database;
    }

    public Services getServices() {
        return services;
    }

    public MultiLineAPI getLineAPI() { return lineAPI; }

    private void startExhaustionTask() {
        // Run first 30min after server starts, then every 60min after
        // If the server restarts a lot this means the delay after it's finished restarting will be 30min,
        // but if it's running constantly 30min is going to be about the average time it _could_ have been.
        // We could be smarter than this and actually store the last time it ran, and start it back up with the
        // correct delay each time if we wanted.
        new ExhaustionTask(this).runTaskTimer(this, 36000L, 72000L);
    }

}
