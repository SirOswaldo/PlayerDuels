/*
 * Copyright (C) 2020 sir_oswaldo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package siroswaldo.playerduels.util.yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Yaml {

    private final JavaPlugin plugin;
    private final String name;
    private File file;
    private FileConfiguration fileConfiguration;

    public Yaml(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name+".yml";
    }

    /**
     * Obtener el archivo de configuracion
     * @return fileConfiguration
     */
    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            reloadFile();
        }
        return fileConfiguration;
    }
    
    /**
     * Recarga el archivo
     */
    public void reloadFile() {
        if (fileConfiguration == null) {
            file = new File(plugin.getDataFolder(), name);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        Reader defConfigStream = new InputStreamReader(plugin.getResource(name), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        fileConfiguration.setDefaults(defConfig);
    }

    /**
     * Guarda el archivo
     */
    public void saveFile() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "No se pudo guardar el archivo.");
        }
    }

    /**
     * Registra el archivo
     */
    public void registerFile() {
        file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            getFileConfiguration().options().copyDefaults(true);
            saveFile();
        }
    }
}

