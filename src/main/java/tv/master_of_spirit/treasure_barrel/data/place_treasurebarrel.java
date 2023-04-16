package tv.master_of_spirit.treasure_barrel.data;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTables;
import tv.master_of_spirit.treasure_barrel.Treasure_barrel;


public class place_treasurebarrel implements Listener {
    @EventHandler
    public void PlaceBlockEvent(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType().equals(Material.BARREL)) {
            if (event.getItemInHand().hasItemMeta()) {
                NamespacedKey key = new NamespacedKey(Treasure_barrel.getPlugin(Treasure_barrel.class), "treasurebarrel");
                if (event.getItemInHand().getItemMeta().getCustomTagContainer().getCustomTag(key, ItemTagType.STRING).equals("treasure_barrel")) {
                    Barrel barrel = (Barrel) event.getBlock().getState();
                    // create LootContext
                    LootContext lootContext = new LootContext.Builder(event.getBlockPlaced().getLocation()).build();
                    // fill the barrel with the loot
                    LootTables.BURIED_TREASURE.getLootTable().fillInventory(barrel.getInventory(), null, lootContext);
                }
            }
        }
    }
}
