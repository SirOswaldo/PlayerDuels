package siroswaldo.playerduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.util.message.ListMessage;
import siroswaldo.playerduels.util.message.StringMessage;

public class PlayerDuelsAdmin implements CommandExecutor {

    private final PlayerDuels playerDuels;

    public PlayerDuelsAdmin(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
        String prefix = messages.getString("prefix");
        if (args.length > 0){
            switch (args[0].toLowerCase()){
                case "reload":
                    playerDuels.getConfiguration().reloadFile();
                    playerDuels.getMessages().reloadFile();
                    messages = playerDuels.getMessages().getFileConfiguration();
                    prefix = messages.getString("prefix");
                    StringMessage reloadComplete = new StringMessage(prefix + messages.getString("reloadComplete"));
                    sender.sendMessage(reloadComplete.addColor());
                    break;
                case "version":
                    ListMessage version = new ListMessage();
                    version.addLine("");
                    version.addLine("");
                    version.addLine("");
                    version.addLine("");
                    version.sendMessage(sender);
                    break;
                default:
                    StringMessage invalidArgument = new StringMessage(prefix + messages.getString("invalidArgument"));
                    sender.sendMessage(invalidArgument.addColor());
                    break;
            }
        } else {
            StringMessage noArgument = new StringMessage(prefix + messages.getString("noArgument"));
            sender.sendMessage(noArgument.addColor());
        }
        return true;
    }
}
