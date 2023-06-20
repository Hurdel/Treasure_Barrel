package tv.master_of_spirit.treasure_barrel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.loot.LootTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class replace_treasuremap implements Listener {

    public int spawnpercentage = 75;

    @EventHandler
    public void GenerateLootEvent(LootGenerateEvent event) {
        if (event.getLootTable().getKey().equals(LootTables.SHIPWRECK_MAP.getKey())) {
            Random random = new Random();
            int randomNumber = random.nextInt(4);
            // get meta & ItemStack
            ItemStack treasurebarrel = new ItemStack(Material.BARREL);
            ItemMeta barrelmeta = treasurebarrel.getItemMeta();
            // name
            barrelmeta.setDisplayName(ChatColor.ITALIC + "Treasure Barrel");
            // customtag
            barrelmeta.getCustomTagContainer().setCustomTag(treasure_barrel.getPlugin(treasure_barrel.class).key, ItemTagType.STRING, "treasure_barrel");
            // setmeta
            treasurebarrel.setItemMeta(barrelmeta);

            try {
                File myObj = new File("treasure_barrel.yml");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String nextLine = myReader.nextLine();
                    if (nextLine.contains("treasure_barrel:")) {
                        spawnpercentage = Integer.parseInt(nextLine.split(" ")[1]);
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "can't read the config-file!" + ChatColor.RESET);
                e.printStackTrace();
            }

            if (randomNumber > spawnpercentage) {
                // add treasure barrel
                event.getLoot().add(treasurebarrel);
            }
            try {
                for (ItemStack item : event.getLoot()) {
                    if (item.getType().equals(Material.FILLED_MAP)) {
                        event.getLoot().remove(item);
                        break;
                    }
                }
            }
            catch (Exception ignored) {
            }
        }
    }
}
