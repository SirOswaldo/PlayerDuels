package siroswaldo.playerduels.inventories.arenaditor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.arena.Arena;
import siroswaldo.playerduels.util.inventory.InventoryData;
import siroswaldo.playerduels.util.itemstack.ItemCreator;
import siroswaldo.playerduels.util.message.StringMessage;

public class ArenaEditorInventory {

    private final PlayerDuels playerDuels;

    private final Inventory inventory;
    private final Player player;
    private final Arena arena;

    public ArenaEditorInventory(PlayerDuels playerDuels, Player player, Arena arena) {
        this.inventory = Bukkit.createInventory(null, 27, new StringMessage("Edit Arena: " + arena.getName()).addColor());
        this.playerDuels = playerDuels;
        this.player = player;
        this.arena = arena;
        InventoryData inventoryData = new InventoryData("edit-arena");
        inventoryData.getData().put("mode", "normal");
        inventoryData.getData().put("arena", arena);
        playerDuels.getInventoryData().put(player.getUniqueId(), inventoryData);
    }

    public void openInventory(){
        ItemCreator panel = new ItemCreator(Material.RED_STAINED_GLASS_PANE);
        panel.setDisplayName("&7", true);
        for (int i = 0;i < 27; i++){
            inventory.setItem(i, panel.getItem());
        }
        // Close
        ItemCreator close = new ItemCreator(Material.IRON_DOOR);
        close.setDisplayName("&cCerrar", true);
        close.addLoreLine("&7", false);
        close.addLoreLine("&7Click para cerrar", true);
        inventory.setItem(10, close.getItem());
        // Set Hub
        Location hubLocation = arena.getHubLocation();
        ItemCreator hub = new ItemCreator(Material.BEACON);
        hub.setDisplayName("&6Hub Location", true);
        if (hubLocation == null){
            hub.addLoreLine("&cNo establecido", true);
            hub.addLoreLine("&7", false);
            hub.addLoreLine("&7Click para establecer", true);
        } else {
            hub.addLoreLine("&6World: &e" + hubLocation.getWorld().getName(), true);
            hub.addLoreLine("&6X: &e" + hubLocation.getX(), true);
            hub.addLoreLine("&6Y: &e" + hubLocation.getY(), true);
            hub.addLoreLine("&6Z: &e" + hubLocation.getZ(), true);
            hub.addLoreLine("&6Yaw: &e" + hubLocation.getYaw(), true);
            hub.addLoreLine("&6Pitch: &e" + hubLocation.getPitch(), true);
            hub.addLoreLine("&7", false);
            hub.addLoreLine("&7Click para cambiar", true);
        }
        inventory.setItem(12, hub.getItem());
        // Set Challenger Spawn
        Location challengerLocation = arena.getChallengerLocation();
        ItemCreator challenger = new ItemCreator(Material.PAPER);
        challenger.setDisplayName("&6Challenger Spawn Location", true);
        if (challengerLocation == null){
            challenger.addLoreLine("&cNo establecido", true);
            challenger.addLoreLine("&7", false);
            challenger.addLoreLine("&7Click para cambiar", true);
        } else {
            challenger.addLoreLine("&6World: &e" + challengerLocation.getWorld().getName(), true);
            challenger.addLoreLine("&6X: &e" + challengerLocation.getX(), true);
            challenger.addLoreLine("&6Y: &e" + challengerLocation.getY(), true);
            challenger.addLoreLine("&6Z: &e" + challengerLocation.getZ(), true);
            challenger.addLoreLine("&6Yaw: &e" + challengerLocation.getYaw(), true);
            challenger.addLoreLine("&6Pitch: &e" + challengerLocation.getPitch(), true);
            challenger.addLoreLine("&7", false);
            challenger.addLoreLine("&7Click para cambiar", true);
        }
        inventory.setItem(13, challenger.getItem());
        // Set Challenged Spawn
        Location challengedLocation = arena.getChallengedLocation();
        ItemCreator challenged = new ItemCreator(Material.PAPER);
        challenged.setDisplayName("&6Challenger Spawn Location", true);
        if (challengerLocation == null){
            challenged.addLoreLine("&cNo establecido", true);
            challenged.addLoreLine("&7", false);
            challenged.addLoreLine("&7Click para cambiar", true);
        } else {
            challenged.addLoreLine("&6World: &e" + challengedLocation.getWorld().getName(), true);
            challenged.addLoreLine("&6X: &e" + challengedLocation.getX(), true);
            challenged.addLoreLine("&6Y: &e" + challengedLocation.getY(), true);
            challenged.addLoreLine("&6Z: &e" + challengedLocation.getZ(), true);
            challenged.addLoreLine("&6Yaw: &e" + challengedLocation.getYaw(), true);
            challenged.addLoreLine("&6Pitch: &e" + challengedLocation.getPitch(), true);
            challenged.addLoreLine("&7", false);
            challenged.addLoreLine("&7Click para cambiar", true);
        }
        inventory.setItem(14, challenged.getItem());
        // Enable Switch
        ItemCreator enable;
        if (arena.isEnable()){
            enable = new ItemCreator(Material.GREEN_WOOL);
            enable.setDisplayName("&aActivado", true);
            enable.addLoreLine("&7", false);
            enable.addLoreLine("&7Click para desactivar", true);
        } else {
            enable = new ItemCreator(Material.RED_WOOL);
            enable.setDisplayName("&cDesactivado", true);
            enable.addLoreLine("&7", false);
            enable.addLoreLine("&7Click para activar", true);
        }
        inventory.setItem(15, enable.getItem());
        // Delete Arena
        ItemCreator delete = new ItemCreator(Material.BARRIER);
        delete.setDisplayName("&cEliminar arena", true);
        delete.addLoreLine("&7", false);
        delete.addLoreLine("&7Click para eliminar la arena", true);
        inventory.setItem(16, delete.getItem());
        player.openInventory(inventory);
    }


}
