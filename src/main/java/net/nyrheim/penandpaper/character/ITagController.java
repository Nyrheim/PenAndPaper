package net.nyrheim.penandpaper.character;

import net.iso2013.mlapi.api.tag.TagController;
import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class ITagController implements TagController {

    private final PenAndPaper plugin;
    private final ITagController.TagLine line;

    public ITagController(PenAndPaper plugin) {
        this.plugin = plugin;
        this.line = new IPlayerTagLine(plugin);
    }

    @Override
    public List<TagLine> getFor(Entity entity) {
        return Collections.singletonList(this.line);
    }

    @Override
    public String getName(Entity target, Player viewer, String previous) {
        return null;
    }


    @Override
    public EntityType[] getAutoApplyFor() {
        return new EntityType[] {
                EntityType.PLAYER
        };
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int getNamePriority() {
        return 0;
    }
}
