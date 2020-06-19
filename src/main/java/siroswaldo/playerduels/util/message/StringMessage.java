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
public class StringMessage {

    private String message;

    public StringMessage() {
        message = "";
    }

    public StringMessage(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String appendText(String text) {
        message = message + text;
        return message;
    }

    public void replaceAll(String arg0, String arg1) {
        message = message.replaceAll(arg0, arg1);
    }

    public String addColor() {
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public String removeColor() {
        message = ChatColor.stripColor(message);
        return message;
    }

    public void sendMessage(CommandSender commandSender) {
        commandSender.sendMessage(message);
    }

    public void sendMessage(ConsoleCommandSender consoleCommandSender) {
        consoleCommandSender.sendMessage(message);
    }

    public void sendMessage(Player player) {
        player.sendMessage(message);
    }

    public void announceMessage(Server server, String permission) {
        server.broadcast(message, permission);
    }

    public void announceMessage(Server server) {
        server.broadcastMessage(message);
    }

    public void logConsole(){
        Bukkit.getConsoleSender().sendMessage(message);
    }
    @Override
    public String toString(){
        return message;
    }
}
