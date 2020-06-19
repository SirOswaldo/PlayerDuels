package siroswaldo.playerduels.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.util.message.StringMessage;

import java.util.UUID;

public class DuelAccept implements CommandExecutor {

    private final PlayerDuels playerDuels;

    public DuelAccept(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
        String prefix = messages.getString("prefix");
        if (sender instanceof Player){
            Player challenged = (Player) sender;
            if (playerDuels.getPetitions().containsKey(challenged.getUniqueId())){
                UUID challengerUUID = playerDuels.getPetitions().get(challenged.getUniqueId());
                OfflinePlayer challenger = playerDuels.getServer().getOfflinePlayer(challengerUUID);
                if (challenger.isOnline()){

                } else {
                    StringMessage challengerDisconnected = new StringMessage(prefix + messages.getString("duelAccept.challengerDisconnected"));
                    challenged.sendMessage(challengerDisconnected.addColor());
                }
            } else {
                StringMessage noPetition = new StringMessage(prefix + messages.getString("duelAccept.noPetition"));
                challenged.sendMessage(noPetition.addColor());
            }
        } else {
            StringMessage senderIsConsole = new StringMessage(prefix + messages.getString("duelAccept.senderIsConsole"));
            sender.sendMessage(senderIsConsole.addColor());
        }
        return true;
    }
}
