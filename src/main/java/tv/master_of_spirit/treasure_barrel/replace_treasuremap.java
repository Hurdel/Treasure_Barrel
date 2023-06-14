package tv.master_of_spirit.treasure_barrel;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.loot.LootTables;

import java.util.Random;

public class replace_treasuremap implements Listener {
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

            if (randomNumber != 3) {
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