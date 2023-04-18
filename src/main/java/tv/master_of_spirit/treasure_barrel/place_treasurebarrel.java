package tv.master_of_spirit.treasure_barrel;

import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTables;


public class place_treasurebarrel implements Listener {
    @EventHandler
    public void PlaceBlockEvent(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType().equals(Material.BARREL)) {
            if (event.getItemInHand().hasItemMeta()) {
                if (event.getItemInHand().getItemMeta().getCustomTagContainer().getCustomTag(Treasure_barrel.getPlugin(Treasure_barrel.class).key, ItemTagType.STRING) != null) {
                    if (event.getItemInHand().getItemMeta().getCustomTagContainer().getCustomTag(Treasure_barrel.getPlugin(Treasure_barrel.class).key, ItemTagType.STRING).equals("treasure_barrel")) {
                        Barrel barrel = (Barrel) event.getBlock().getState();
                        // create LootContext
                        LootContext lootContext = new LootContext.Builder(event.getBlock().getLocation()).build();
                        // fill the barrel with the loot
                        LootTables.BURIED_TREASURE.getLootTable().fillInventory(barrel.getInventory(), null, lootContext);
                    }
                }
            }
        }
    }
}
