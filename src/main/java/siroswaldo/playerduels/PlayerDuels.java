package siroswaldo.playerduels;

import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.playerduels.commands.DuelPetition;
import siroswaldo.playerduels.commands.PlayerDuelsAdmin;
import siroswaldo.playerduels.util.yaml.Yaml;

public final class PlayerDuels extends JavaPlugin {

    //commands
    private DuelPetition duelPetition = new DuelPetition(this);

    private Yaml configuration = new Yaml(this, "configuration");
    private Yaml messages = new Yaml(this, "messages");

    @Override
    public void onEnable() {
        registerYamls();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands(){
        getCommand("playerduelsadmin").setExecutor(duelPetition);
    }

    public DuelPetition getDuelPetition() {
        return duelPetition;
    }

    private void registerYamls(){
        configuration.registerFile();
        messages.registerFile();
    }

    public Yaml getConfiguration() {
        return configuration;
    }

    public Yaml getMessages() {
        return messages;
    }
}
