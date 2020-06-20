package siroswaldo.playerduels.inventories.arenaselector;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.arena.Arena;
import siroswaldo.playerduels.duel.Duel;
import siroswaldo.playerduels.util.inventory.InventoryData;
import siroswaldo.playerduels.util.task.InventoryOpenTask;

import java.util.UUID;

public class ArenaSelectorListener implements Listener {

    private final PlayerDuels playerDuels;

    public ArenaSelectorListener(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Arena Selector")){
            Player player = (Player) event.getWhoClicked();
            UUID uuid = player.getUniqueId();
            if (playerDuels.getInventoryData().containsKey(uuid)){
                InventoryData inventoryData = playerDuels.getInventoryData().get(uuid);
                if (inventoryData.getType().equals("arena-selector")){
                    ItemStack item = event.getCurrentItem();
                    if (item != null && item.getItemMeta().hasDisplayName()){
                        String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                        Arena arena = playerDuels.getArenas().get(name);
                        if (arena.getStatus().equals("waiting")){
                            playerDuels.getInventoryData().remove(uuid);
                            player.closeInventory();
                            Duel duel = (Duel) inventoryData.getData().get("duel");
                            duel.setStatus("fighting");
                            arena.setStatus("fighting");
                            arena.setDuel(duel);
                            duel.getChallenger().teleport(arena.getChallengerLocation());
                            duel.getChallenged().teleport(arena.getChallengedLocation());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (playerDuels.getInventoryData().containsKey(uuid)){
            InventoryData inventoryData = playerDuels.getInventoryData().get(uuid);
            if (inventoryData.getType().equals("arena-selector")){
                InventoryOpenTask inventoryOpenTask = new InventoryOpenTask(playerDuels, player, event.getInventory());
                inventoryOpenTask.startScheduler();
            }
        }
    }
}
