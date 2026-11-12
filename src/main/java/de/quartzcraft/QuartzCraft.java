package de.quartzcraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuartzCraft extends JavaPlugin implements Listener {

    private NamespacedKey recipeKey;

    @Override
    public void onEnable() {
        recipeKey = new NamespacedKey(this, "quartz_block_to_quartz");

        // Falls das Plugin neu geladen wird, altes Rezept zuerst entfernen.
        Bukkit.removeRecipe(recipeKey);

        ShapelessRecipe recipe = new ShapelessRecipe(recipeKey, new ItemStack(Material.QUARTZ, 4));
        recipe.addIngredient(Material.QUARTZ_BLOCK);
        Bukkit.addRecipe(recipe);

        getServer().getPluginManager().registerEvents(this, this);

        // Rezept auch fuer schon eingeloggte Spieler im Rezeptbuch freischalten.
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.discoverRecipe(recipeKey);
        }
    }

    @Override
    public void onDisable() {
        if (recipeKey != null) {
            Bukkit.removeRecipe(recipeKey);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().discoverRecipe(recipeKey);
    }
}
