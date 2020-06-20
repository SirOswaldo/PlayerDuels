package siroswaldo.playerduels.arena;

import org.bukkit.Location;
import siroswaldo.playerduels.duel.Duel;

public class Arena {

    private final String name;
    private boolean enable;

    private Location hubLocation;
    private Location challengerLocation;
    private Location challengedLocation;

    private String status;
    private Duel duel;

    public Arena(String name, Location hubLocation, Location challengerLocation, Location challengedLocation) {
        this.name = name;
        this.hubLocation = hubLocation;
        this.challengerLocation = challengerLocation;
        this.challengedLocation = challengedLocation;
        enable = (challengerLocation != null) && (challengedLocation != null);
        if (enable){
            status = "waiting";
        } else {
            status = "need-edit";
        }
    }

    public String getName() {
        return name;
    }

    public Location getHubLocation() {
        return hubLocation;
    }

    public void setHubLocation(Location hubLocation) {
        this.hubLocation = hubLocation;
    }

    public Location getChallengerLocation() {
        return challengerLocation;
    }

    public void setChallengerLocation(Location challengerLocation) {
        this.challengerLocation = challengerLocation;
    }

    public Location getChallengedLocation() {
        return challengedLocation;
    }

    public void setChallengedLocation(Location challengedLocation) {
        this.challengedLocation = challengedLocation;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Duel getDuel() {
        return duel;
    }

    public void setDuel(Duel duel) {
        this.duel = duel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
