package siroswaldo.playerduels.util.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class YamlFileMaker {

    private Yaml file;

    public YamlFileMaker(JavaPlugin javaPlugin, String name){
        file = new Yaml(javaPlugin, name);
    }

    public void register(){
        file.registerFile();
        load();
    }

    public void reload(){
        file.reloadFile();
        load();
    }

    public FileConfiguration get(){
        return file.getFileConfiguration();
    }

    public abstract void load();

}
