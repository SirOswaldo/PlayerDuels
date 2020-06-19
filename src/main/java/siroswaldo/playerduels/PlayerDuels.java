package siroswaldo.playerduels;

import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.playerduels.commands.*;
import siroswaldo.playerduels.util.yaml.Yaml;

import java.util.HashMap;
import java.util.UUID;

public final class PlayerDuels extends JavaPlugin {

    private final Yaml configuration = new Yaml(this, "configuration");
    private final Yaml messages = new Yaml(this, "messages");

    private final HashMap<UUID, UUID> petitions = new HashMap<>();

    @Override
    public void onEnable() {
        registerYamls();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands(){
        getCommand("playerduelsadmin").setExecutor(new PlayerDuelsAdmin(this));
        getCommand("duelpetition").setExecutor(new DuelPetition(this));
        getCommand("duelaccept").setExecutor(new DuelAccept(this));
        getCommand("duelreject").setExecutor(new DuelReject(this));
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

    public HashMap<UUID, UUID> getPetitions() {
        return petitions;
    }
}
