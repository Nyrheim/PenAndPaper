package net.nyrheim.penandpaper.item;

import net.nyrheim.penandpaper.PenAndPaper;

public final class PenRecipeService {

    private final PenAndPaper plugin;

    public PenRecipeService(PenAndPaper plugin) {
        this.plugin = plugin;
        plugin.getServer().clearRecipes();
    }
}
