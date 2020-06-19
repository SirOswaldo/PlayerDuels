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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author sir_oswaldo
 */
public class YamlFiles {

    private final JavaPlugin magicPunishments;
    private final Map<String, Yaml> yamlFiles;

    public YamlFiles(JavaPlugin magicPunishments) {
        this.magicPunishments = magicPunishments;
        this.yamlFiles = new HashMap<>();
    }

    public void addFile(String file) {
        yamlFiles.put(file, new Yaml(magicPunishments, file));
    }

    public void registerFiles() {
        Set<String> files = yamlFiles.keySet();
        for (String file : files) {
            yamlFiles.get(file).registerFile();
        }
    }

    public void reloadFiles() {
        Set<String> files = yamlFiles.keySet();
        for (String file : files) {
            yamlFiles.get(file).reloadFile();
        }
    }

    public void saveFiles() {
        Set<String> files = yamlFiles.keySet();
        for (String file : files) {
            yamlFiles.get(file).saveFile();
        }
    }

    public FileConfiguration getFileConfiguration(String file) {
        return yamlFiles.get(file).getFileConfiguration();
    }

    public void registerFile(String file) {
        yamlFiles.get(file).registerFile();
    }

    public void reloadFile(String file) {
        yamlFiles.get(file).reloadFile();
    }

    public void saveFileConfiguration(String file) {
        yamlFiles.get(file).saveFile();
    }
}
