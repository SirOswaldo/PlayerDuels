package siroswaldo.playerduels.duel;

import org.bukkit.entity.Player;

public class Duel {

    private final Player challenger;
    private final Player challenged;

    private String status;

    public Duel(Player challenger, Player challenged) {
        this.challenger = challenger;
        this.challenged = challenged;
        status = "arena-selector";
    }

    public Player getChallenger() {
        return challenger;
    }

    public Player getChallenged() {
        return challenged;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

}
