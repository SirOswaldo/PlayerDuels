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
package siroswaldo.playerduels.util.message;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author sir_oswaldo
 */
public class ListMessage {

    private List<String> message;

    public ListMessage() {
        message = new ArrayList<>();
    }

    public ListMessage(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public void replaceAll(String arg0, String arg1) {
        for (int i = 0; i < message.size(); i++) {
            message.set(i, message.get(i).replaceAll(arg0, arg1));
        }
    }

    public void addColor() {
        for (int i = 0; i < message.size(); i++) {
            message.set(i, ChatColor.translateAlternateColorCodes('&', message.get(i)));
        }
    }

    public void removeColor() {
        for (int i = 0; i < message.size(); i++) {
            message.set(i, ChatColor.stripColor(message.get(i)));
        }
    }

    public void sendMessage(CommandSender sender) {
        for (String line : message) {
            sender.sendMessage(line);
        }
    }

    public void sendMessage(ConsoleCommandSender console) {
        for (String line : message) {
            console.sendMessage(line);
        }
    }

    public void sendMessage(Player player) {
        for (String line : message) {
            player.sendMessage(line);
        }
    }

    public void announceMessage(Server server, String permission) {
        for (String line : message) {
            server.broadcast(line, permission);
        }
    }

    public void announceMessage(Server server) {
        for (String line : message) {
            server.broadcastMessage(line);
        }
    }

    public void log(){
        for (String line : message){
            Bukkit.getConsoleSender().sendMessage(line);
        }
    }

    public void addLine(String text){
        message.add(text);
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < message.size(); i++) {
            string.append(message.get(i));
            if (i != message.size() - 1) {
                string.append("\n");
            }
        }
        return string.toString();
    }
}
