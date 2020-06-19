package siroswaldo.playerduels.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DuelPetitionReceivedEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();

    private boolean cancel;

    private final Player challenger;
    private final Player challenged;

    public DuelPetitionReceivedEvent(Player challenger, Player challenged) {
        this.challenger = challenger;
        this.challenged = challenged;
    }

    public Player getChallenger() {
        return challenger;
    }

    public Player getChallenged() {
        return challenged;
    }

    @Override
    public String getEventName() {
        return "DuelPetitionReceivedEvent";
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
