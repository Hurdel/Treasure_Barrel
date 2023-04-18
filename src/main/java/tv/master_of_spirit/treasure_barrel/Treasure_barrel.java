package tv.master_of_spirit.treasure_barrel;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Treasure_barrel extends JavaPlugin implements Listener {

    public NamespacedKey key = NamespacedKey.minecraft(NamespacedKey.MINECRAFT);

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new replace_treasuremap(), this);
        Bukkit.getPluginManager().registerEvents(new place_treasurebarrel(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
