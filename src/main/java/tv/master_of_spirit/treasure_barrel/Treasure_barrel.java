package tv.master_of_spirit.treasure_barrel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import tv.master_of_spirit.treasure_barrel.data.place_treasurebarrel;
import tv.master_of_spirit.treasure_barrel.data.replace_treasuremap;

import java.util.ArrayList;

public final class Treasure_barrel extends JavaPlugin implements Listener {

    NamespacedKey key = new NamespacedKey(this, "treasurebarrel");

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new replace_treasuremap(), this);
        Bukkit.getPluginManager().registerEvents(new place_treasurebarrel(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            switch (command.getName()) {
                case "treasurebarrel":
                    ItemStack treasurebarrel = new ItemStack(Material.BARREL);
                    ItemMeta barrelmeta = treasurebarrel.getItemMeta();
                    // name
                    if (barrelmeta != null) {
                        barrelmeta.setDisplayName(ChatColor.ITALIC + "Treasure Barrel");
                        // lore
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(ChatColor.RESET + "" + ChatColor.AQUA + "Place this barrel to get the Loot");
                        barrelmeta.setLore(lore);
                    }
                    // setmeta & replace map
                    treasurebarrel.setItemMeta(barrelmeta);
                    ((Player) sender).getInventory().addItem(treasurebarrel);
            }
        }
        return true;
    }
}
