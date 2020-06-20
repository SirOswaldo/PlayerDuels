package siroswaldo.playerduels.duel;

import org.bukkit.entity.Player;

public class Duel {

    private final Player challenger;
    private final Player challenged;

    public Duel(Player challenger, Player challenged) {
        this.challenger = challenger;
        this.challenged = challenged;
    }

    public Player getChallenger() {
        return challenger;
    }

    public Player getChallenged() {
        return challenged;
    }
}
