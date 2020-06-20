package siroswaldo.playerduels.inventories.arenaselector;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.arena.Arena;
import siroswaldo.playerduels.duel.Duel;
import siroswaldo.playerduels.util.inventory.InventoryData;
import siroswaldo.playerduels.util.itemstack.ItemCreator;

import java.util.Random;

public class ArenaSelectorInventory {

    private final PlayerDuels playerDuels;
    private final Inventory inventory;
    private final Duel duel;

    public ArenaSelectorInventory(PlayerDuels playerDuels, Duel duel) {
        this.playerDuels = playerDuels;
        this.duel = duel;
        inventory = Bukkit.createInventory(null, 54, "Arena Selector");
    }

    public void openInventory(){
        FileConfiguration config = playerDuels.getConfiguration().getFileConfiguration();
        int index = 0;
        for (String name:playerDuels.getArenas().keySet()){
            Arena arena = playerDuels.getArenas().get(name);
            if (arena.getStatus().equals("waiting")){
                ItemCreator item = new ItemCreator(Material.PAPER);
                item.setDisplayName("&e" + arena.getName(), true);
                inventory.setItem(index, item.getItem());
            }
        }
        // Open
        InventoryData inventoryData = new InventoryData("arena-selector");
        inventoryData.getData().put("duel", duel);
        String arenaSelector = config.getString("arenaSelector");
        switch (arenaSelector.toLowerCase()){
            case "challenger":
                duel.getChallenger().openInventory(inventory);
                playerDuels.getInventoryData().put(duel.getChallenger().getUniqueId(), inventoryData);
                break;
            case "challenged":
                duel.getChallenged().openInventory(inventory);
                playerDuels.getInventoryData().put(duel.getChallenged().getUniqueId(), inventoryData);
                break;
            case "random":
                Random random = new Random();
                int result = random.nextInt(2);
                if (result == 0){
                    duel.getChallenger().openInventory(inventory);
                    playerDuels.getInventoryData().put(duel.getChallenger().getUniqueId(), inventoryData);
                } else {
                    duel.getChallenged().openInventory(inventory);
                    playerDuels.getInventoryData().put(duel.getChallenged().getUniqueId(), inventoryData);
                }
                break;
        }
    }
}
