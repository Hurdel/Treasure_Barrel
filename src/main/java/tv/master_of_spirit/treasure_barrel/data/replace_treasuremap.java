package tv.master_of_spirit.treasure_barrel.data;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.loot.LootTables;
import tv.master_of_spirit.treasure_barrel.Treasure_barrel;


import java.util.ArrayList;

public class replace_treasuremap implements Listener {

    @EventHandler
    public void GenerateLootEvent(LootGenerateEvent event) {
        if (event.getLootTable().getKey().equals(LootTables.SHIPWRECK_MAP.getKey())) {
            try {
                for (ItemStack item : event.getLoot()) {
                    if (item.getType().equals(Material.FILLED_MAP)) {
                        // get meta & ItemStack
                        ItemStack treasurebarrel = new ItemStack(Material.BARREL);
                        ItemMeta barrelmeta = treasurebarrel.getItemMeta();
                        // name
                        barrelmeta.setDisplayName(ChatColor.ITALIC + "Treasure Barrel");
                        // customtag
                        NamespacedKey key = new NamespacedKey(Treasure_barrel.getPlugin(Treasure_barrel.class), "treasurebarrel");
                        barrelmeta.getCustomTagContainer().setCustomTag(key, ItemTagType.STRING, "treasure_barrel");
                        // setmeta & replace map
                        treasurebarrel.setItemMeta(barrelmeta);
                        event.getLoot().add(treasurebarrel);
                        event.getLoot().remove(item);
                    }
                }
            }
            catch (Exception ignored) {
            }
        }
    }
}
