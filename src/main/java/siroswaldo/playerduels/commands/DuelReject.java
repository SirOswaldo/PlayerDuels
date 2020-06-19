package siroswaldo.playerduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.util.message.StringMessage;

public class DuelReject implements CommandExecutor {

    private final PlayerDuels playerDuels;

    public DuelReject(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
        String prefix = messages.getString("prefix");
        if (sender instanceof Player){
            Player challenged = (Player) sender;
            if (playerDuels.getPetitions().containsKey(challenged.getUniqueId())){
                playerDuels.getPetitions().remove(challenged.getUniqueId());
                StringMessage rejected = new StringMessage(prefix + messages.getString("duelReject.rejected"));
                challenged.sendMessage(rejected.addColor());
            } else {
                StringMessage noPetition = new StringMessage(prefix + messages.getString("duelReject.noPetition"));
                challenged.sendMessage(noPetition.addColor());
            }
        } else {
            StringMessage senderIsConsole = new StringMessage(prefix + messages.getString("duelReject.senderIsConsole"));
            sender.sendMessage(senderIsConsole.addColor());
        }
        return true;
    }
}
