package siroswaldo.playerduels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.duel.Duel;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    private final PlayerDuels playerDuels;

    public PlayerQuitListener(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (playerDuels.getDuels().containsKey(uuid)){
            Duel duel = playerDuels.getDuels().get(uuid);

        }
    }
}
