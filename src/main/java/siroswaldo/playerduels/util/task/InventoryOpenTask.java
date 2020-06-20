package siroswaldo.playerduels.util.task;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryOpenTask extends Task {

    private final Player player;
    private final Inventory inventory;

    public InventoryOpenTask(JavaPlugin plugin,Player player, Inventory inventory) {
        super(plugin, 10L);
        this.player = player;
        this.inventory = inventory;
    }

    public void actions() {
        if(player.isOnline()){
            player.openInventory(inventory);
            stopScheduler();
        }
    }
}