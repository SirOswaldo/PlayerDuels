package siroswaldo.playerduels.tasks;

import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.util.task.Task;

import java.util.UUID;

public class PetitionTimer extends Task {

    private final PlayerDuels playerDuels;
    private final UUID challenger;
    private final UUID challenged;
    private int duration;

    public PetitionTimer(PlayerDuels playerDuels, UUID challenger, UUID challenged) {
        super(playerDuels, 20L);
        this.playerDuels = playerDuels;
        this.challenger = challenger;
        this.challenged = challenged;
        duration = playerDuels.getConfiguration().getFileConfiguration().getInt("petition.duration");
    }

    @Override
    public void actions() {
        if (!playerDuels.getPetitions().containsKey(challenged)){
            stopScheduler();
        }
        if (!playerDuels.getPetitions().get(challenged).equals(challenger)){
            stopScheduler();
        }
        if (duration == 0){
            playerDuels.getPetitions().remove(challenged);
            stopScheduler();
        }
        duration--;
    }
}
