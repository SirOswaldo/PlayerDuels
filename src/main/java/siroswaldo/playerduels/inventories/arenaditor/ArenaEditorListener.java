package siroswaldo.playerduels.inventories.arenaditor;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.arena.Arena;
import siroswaldo.playerduels.util.inventory.InventoryData;
import siroswaldo.playerduels.util.itemstack.ItemCreator;
import siroswaldo.playerduels.util.message.StringMessage;
import siroswaldo.playerduels.util.task.InventoryOpenTask;

import java.util.UUID;

public class ArenaEditorListener implements Listener {

    private final PlayerDuels playerDuels;

    public ArenaEditorListener(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (event.getView().getTitle().startsWith("Edit Arena: ")){
            event.setCancelled(true);
            if (playerDuels.getInventoryData().containsKey(uuid)){
                InventoryData inventoryData = playerDuels.getInventoryData().get(uuid);
                Arena arena = (Arena) inventoryData.getData().get("arena");
                if (event.getRawSlot() == 10){
                    playerDuels.getInventoryData().remove(uuid);
                    player.closeInventory();
                    FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                    String prefix = messages.getString("prefix");
                    StringMessage editMenuClose = new StringMessage(prefix + messages.getString("PlayerDuelsArena.editMenuClose"));
                    player.sendMessage(editMenuClose.addColor());
                } else if (event.getRawSlot() == 12){
                    inventoryData.getData().put("mode", "set-hub-location");
                    player.closeInventory();
                    FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                    String prefix = messages.getString("prefix");
                    StringMessage setHubLocation = new StringMessage(prefix + messages.getString("PlayerDuelsArena.setHubLocation"));
                    player.sendMessage(setHubLocation.addColor());
                } else if (event.getRawSlot() == 13){
                    inventoryData.getData().put("mode", "set-challenger-location");
                    player.closeInventory();
                    FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                    String prefix = messages.getString("prefix");
                    StringMessage setChallengerLocation = new StringMessage(prefix + messages.getString("PlayerDuelsArena.setChallengerLocation"));
                    player.sendMessage(setChallengerLocation.addColor());
                } else if (event.getRawSlot() == 14){
                    inventoryData.getData().put("mode", "set-challenged-location");
                    player.closeInventory();
                    FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                    String prefix = messages.getString("prefix");
                    StringMessage setChallengedLocation = new StringMessage(prefix + messages.getString("PlayerDuelsArena.setChallengedLocation"));
                    player.sendMessage(setChallengedLocation.addColor());
                } else if (event.getRawSlot() == 15){
                    if (arena.isEnable()){
                        arena.setEnable(false);
                        ItemCreator enable = new ItemCreator(Material.RED_WOOL);
                        enable.setDisplayName("&cDesactivado", true);
                        enable.addLoreLine("&7", false);
                        enable.addLoreLine("&7Click para activar", true);
                        event.getInventory().setItem(15, enable.getItem());
                    } else {
                        arena.setEnable(true);
                        ItemCreator enable = new ItemCreator(Material.GREEN_WOOL);
                        enable.setDisplayName("&aActivado", true);
                        enable.addLoreLine("&7", false);
                        enable.addLoreLine("&7Click para activar", true);
                        event.getInventory().setItem(15, enable.getItem());
                    }
                    player.updateInventory();
                    playerDuels.saveArena(arena);
                } else if (event.getRawSlot() == 16){
                    playerDuels.deleteArena(arena);
                    playerDuels.getInventoryData().remove(uuid);
                    player.closeInventory();
                    FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                    String prefix = messages.getString("prefix");
                    StringMessage deleted = new StringMessage(prefix + messages.getString("PlayerDuelsArena.deleted"));
                    player.sendMessage(deleted.addColor());
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (event.getView().getTitle().startsWith("Edit Arena: ")){
            if (playerDuels.getInventoryData().containsKey(uuid)){
                InventoryData inventoryData = playerDuels.getInventoryData().get(uuid);
                String mode = (String) inventoryData.getData().get("mode");
                if (mode.equals("normal")){
                    InventoryOpenTask inventoryOpenTask = new InventoryOpenTask(playerDuels, player, event.getInventory());
                    inventoryOpenTask.startScheduler();
                }
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (playerDuels.getInventoryData().containsKey(uuid)){
            InventoryData inventoryData = playerDuels.getInventoryData().get(uuid);
            String mode = (String) inventoryData.getData().get("mode");
            Arena arena = (Arena) inventoryData.getData().get("arena");
            switch (mode){
                case "set-hub-location":
                    event.setCancelled(true);
                    if (event.getMessage().equals("set")){
                        arena.setHubLocation(player.getLocation());
                        playerDuels.getInventoryData().remove(uuid);
                        ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                        arenaEditorInventory.openInventory();
                        playerDuels.saveArena(arena);
                    } else if (event.getMessage().equals("cancel")){
                        playerDuels.getInventoryData().remove(uuid);
                        ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                        arenaEditorInventory.openInventory();
                    } else {
                        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                        String prefix = messages.getString("prefix");
                        StringMessage invalidInputOption = new StringMessage(prefix + messages.getString("PlayerDuelsArena.invalidInputOption"));
                        player.sendMessage(invalidInputOption.addColor());
                    }
                    break;
                case "set-challenger-location":
                    event.setCancelled(true);
                    if (event.getMessage().equals("set")){
                        arena.setChallengerLocation(player.getLocation());
                        playerDuels.getInventoryData().remove(uuid);
                        ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                        arenaEditorInventory.openInventory();
                        playerDuels.saveArena(arena);
                    } else if (event.getMessage().equals("cancel")){
                        playerDuels.getInventoryData().remove(uuid);
                        ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                        arenaEditorInventory.openInventory();
                    } else {
                        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                        String prefix = messages.getString("prefix");
                        StringMessage invalidInputOption = new StringMessage(prefix + messages.getString("PlayerDuelsArena.invalidInputOption"));
                        player.sendMessage(invalidInputOption.addColor());
                    }
                    break;
                case "set-challenged-location":
                    event.setCancelled(true);
                    if (event.getMessage().equals("set")){
                        arena.setChallengedLocation(player.getLocation());
                        playerDuels.getInventoryData().remove(uuid);
                        ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                        arenaEditorInventory.openInventory();
                        playerDuels.saveArena(arena);
                    } else if (event.getMessage().equals("cancel")){
                        playerDuels.getInventoryData().remove(uuid);
                        ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                        arenaEditorInventory.openInventory();
                    } else {
                        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
                        String prefix = messages.getString("prefix");
                        StringMessage invalidInputOption = new StringMessage(prefix + messages.getString("PlayerDuelsArena.invalidInputOption"));
                        player.sendMessage(invalidInputOption.addColor());
                    }
                    break;
            }
        }
    }
}
