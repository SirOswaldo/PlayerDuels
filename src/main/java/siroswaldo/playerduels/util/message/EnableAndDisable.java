package siroswaldo.playerduels.util.message;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EnableAndDisable {

    private final JavaPlugin javaPlugin;
    private final List<String> enable;
    private final List<String> disable;

    public EnableAndDisable(JavaPlugin javaPlugin, String name){
        this.javaPlugin = javaPlugin;
        // Enable
        enable = new ArrayList<>();
        enable.add(name + " &7>>> &aComplemento activado!");
        enable.add("&aVersion &7>>> &e" + javaPlugin.getDescription().getVersion());
        enable.add("&aAuthor &7>>> &eKayTeam");
        enable.add("&aWebSite &7>>> &e" + javaPlugin.getDescription().getWebsite());
        // Disable
        disable = new ArrayList<>();
        disable.add(name + " &7>>> &cComplemento desactivado!");
        disable.add("&7>>> &eAdios :D");
    }

    public void sendEnable(){
        for (String line:enable){
            javaPlugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

    public void sendDisable(){
        for (String line:disable){
            javaPlugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }
}
