package siroswaldo.playerduels;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import siroswaldo.playerduels.arena.Arena;
import siroswaldo.playerduels.commands.*;
import siroswaldo.playerduels.duel.Duel;
import siroswaldo.playerduels.inventories.arenaditor.ArenaEditorListener;
import siroswaldo.playerduels.util.inventory.InventoryData;
import siroswaldo.playerduels.util.yaml.Yaml;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class PlayerDuels extends JavaPlugin {

    private final Yaml configuration = new Yaml(this, "configuration");
    private final Yaml messages = new Yaml(this, "messages");

    private final HashMap<UUID, UUID> petitions = new HashMap<>();
    private final HashMap<String, Arena> arenas = new HashMap<>();
    private final HashMap<UUID, Duel> duels = new HashMap<>();

    private final HashMap<UUID, InventoryData> inventoryData = new HashMap<>();

    @Override
    public void onEnable() {
        registerYamls();
        registerArenas();
        registerCommands();
        registerListeners();
    }

    private void registerArenas(){
        FileConfiguration config = configuration.getFileConfiguration();
        if (config.contains("arenas")){
            Set<String> names = config.getConfigurationSection("arenas").getKeys(false);
            for (String name:names){
                Location hub;
                Location challenger;
                Location challenged;
                if (config.contains("arenas."+name+".locations.hub")){
                    double x,y,z;
                    x = config.getDouble("arenas." + name + ".locations.hub.x");
                    y = config.getDouble("arenas." + name + ".locations.hub.y");
                    z = config.getDouble("arenas." + name + ".locations.hub.z");
                    String worldName = config.getString("arenas." + name + ".locations.hub.world");
                    World world = getServer().getWorld(worldName);
                    if (world == null){
                        continue;
                    }
                    hub = new Location(world, x, y, z);
                } else {
                    continue;
                }
                if (config.contains("arenas."+name+".locations.challenger")){
                    double x,y,z;
                    x = config.getDouble("arenas." + name + ".locations.challenger.x");
                    y = config.getDouble("arenas." + name + ".locations.challenger.y");
                    z = config.getDouble("arenas." + name + ".locations.challenger.z");
                    String worldName = config.getString("arenas." + name + ".locations.challenger.world");
                    World world = getServer().getWorld(worldName);
                    if (world == null){
                        continue;
                    }
                    challenger = new Location(world, x, y, z);
                } else {
                    continue;
                }
                if (config.contains("arenas."+name+".locations.challenged")){
                    double x,y,z;
                    x = config.getDouble("arenas." + name + ".locations.challenged.x");
                    y = config.getDouble("arenas." + name + ".locations.challenged.y");
                    z = config.getDouble("arenas." + name + ".locations.challenged.z");
                    String worldName = config.getString("arenas." + name + ".locations.challenged.world");
                    World world = getServer().getWorld(worldName);
                    if (world == null){
                        continue;
                    }
                    challenged = new Location(world, x, y, z);
                } else {
                    continue;
                }
                Arena arena = new Arena(name, hub, challenger, challenged);
                arenas.put(name, arena);
            }
        }
    }

    public void saveArena(Arena arena){
        FileConfiguration config = configuration.getFileConfiguration();
        config.set("arenas." + arena.getName() + ".locations.hub.x", arena.getHubLocation().getX());
        config.set("arenas." + arena.getName() + ".locations.hub.y", arena.getHubLocation().getY());
        config.set("arenas." + arena.getName() + ".locations.hub.z", arena.getHubLocation().getZ());
        config.set("arenas." + arena.getName() + ".locations.hub.world", arena.getHubLocation().getWorld().getName());

        config.set("arenas." + arena.getName() + ".locations.challenger.x", arena.getChallengerLocation().getX());
        config.set("arenas." + arena.getName() + ".locations.challenger.y", arena.getChallengerLocation().getY());
        config.set("arenas." + arena.getName() + ".locations.challenger.z", arena.getChallengerLocation().getZ());
        config.set("arenas." + arena.getName() + ".locations.challenger.world", arena.getChallengerLocation().getWorld().getName());

        config.set("arenas." + arena.getName() + ".locations.challenged.x", arena.getChallengedLocation().getX());
        config.set("arenas." + arena.getName() + ".locations.challenged.y", arena.getChallengedLocation().getY());
        config.set("arenas." + arena.getName() + ".locations.challenged.z", arena.getChallengedLocation().getZ());
        config.set("arenas." + arena.getName() + ".locations.challenged.world", arena.getChallengedLocation().getWorld().getName());
        configuration.saveFile();
    }

    public void deleteArena(Arena arena){
        FileConfiguration config = configuration.getFileConfiguration();
        Map<String, Object> arenas = config.getConfigurationSection("arenas").getValues(false);
        arenas.remove(arena.getName());
        config.set("arenas", arenas);
        configuration.saveFile();
        this.arenas.remove(arena.getName());
    }

    public void reloadArenas(){
        FileConfiguration config = configuration.getFileConfiguration();
        for (String name:arenas.keySet()){
            if (config.contains("arenas." + name)){
                Location hub;
                Location challenger;
                Location challenged;
                if (config.contains("arenas." + name + ".locations.hub")){
                    double x,y,z;
                    x = config.getDouble("arenas." + name + ".locations.hub.x");
                    y = config.getDouble("arenas." + name + ".locations.hub.y");
                    z = config.getDouble("arenas." + name + ".locations.hub.z");
                    String worldName = config.getString("arenas." + name + ".locations.hub.world");
                    World world = getServer().getWorld(worldName);
                    if (world == null){
                        continue;
                    }
                    hub = new Location(world, x, y, z);
                } else {
                    continue;
                }
                if (config.contains("arenas." + name + ".locations.challenger")){
                    double x,y,z;
                    x = config.getDouble("arenas." + name + ".locations.challenger.x");
                    y = config.getDouble("arenas." + name + ".locations.challenger.y");
                    z = config.getDouble("arenas." + name + ".locations.challenger.z");
                    String worldName = config.getString("arenas." + name + ".locations.challenger.world");
                    World world = getServer().getWorld(worldName);
                    if (world == null){
                        continue;
                    }
                    challenger = new Location(world, x, y, z);
                } else {
                    continue;
                }
                if (config.contains("arenas." + name + ".locations.challenged")){
                    double x,y,z;
                    x = config.getDouble("arenas." + name + ".locations.challenged.x");
                    y = config.getDouble("arenas." + name + ".locations.challenged.y");
                    z = config.getDouble("arenas." + name + ".locations.challenged.z");
                    String worldName = config.getString("arenas." + name + ".locations.challenged.world");
                    World world = getServer().getWorld(worldName);
                    if (world == null){
                        continue;
                    }
                    challenged = new Location(world, x, y, z);
                } else {
                    continue;
                }
                Arena arena = new Arena(name, hub, challenger, challenged);
                arenas.put(name, arena);
            } else {

                arenas.remove(name);
            }
        }
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new ArenaEditorListener(this), this);
        getServer().getPluginManager().registerEvents(new ArenaEditorListener(this), this);
    }

    private void registerCommands(){
        getCommand("playerduelsmanager").setExecutor(new PlayerDuelsManager(this));
        getCommand("playerduelsarena").setExecutor(new PlayerDuelsArena(this));
        getCommand("duelpetition").setExecutor(new DuelPetition(this));
        getCommand("duelaccept").setExecutor(new DuelAccept(this));
        getCommand("duelreject").setExecutor(new DuelReject(this));
    }

    private void registerYamls(){
        configuration.registerFile();
        messages.registerFile();
    }

    public Yaml getConfiguration() { return configuration; }

    public Yaml getMessages() { return messages; }

    public HashMap<UUID, UUID> getPetitions() { return petitions; }

    public HashMap<String, Arena> getArenas() { return arenas; }

    public HashMap<UUID, Duel> getDuels() { return duels; }

    public HashMap<UUID, InventoryData> getInventoryData() { return inventoryData; }
}
