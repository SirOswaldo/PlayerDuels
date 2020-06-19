package siroswaldo.playerduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.util.message.StringMessage;

import java.util.HashMap;
import java.util.UUID;

public class DuelPetition implements CommandExecutor {

    private final PlayerDuels playerDuels;
    private final HashMap<UUID, UUID> senders;
    private final HashMap<UUID, UUID> receivers;

    public DuelPetition(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
        senders = new HashMap<>();
        receivers = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
        String prefix = messages.getString("prefix");
        if (sender instanceof Player){
            Player challenger = (Player) sender;
            if (args.length > 0){
                String playerName = args[0];
                Player challenged = playerDuels.getServer().getPlayerExact(playerName);
                if (challenged == null){
                    StringMessage invalidPlayerName = new StringMessage(prefix + messages.getString("duelPetition.invalidPlayerName"));
                    sender.sendMessage(invalidPlayerName.addColor());
                } else {
                    if (receivers.containsKey(challenged.getUniqueId())){
                        if (receivers.get(challenged.getUniqueId()).equals(challenger.getUniqueId())){
                            StringMessage alreadySent = new StringMessage(prefix + messages.getString("duelPetition.alreadySent"));
                            challenger.sendMessage(alreadySent.addColor());
                        } else {
                            senders.put(challenger.getUniqueId(), challenged.getUniqueId());
                            receivers.put(challenged.getUniqueId(), challenger.getUniqueId());
                            StringMessage sended = new StringMessage(prefix + messages.getString("duelPetition.sended"));
                            sended.replaceAll("%challenged%", challenged.getName());
                            challenger.sendMessage(sended.addColor());
                            StringMessage received = new StringMessage(prefix + messages.getString("duelPetition.received"));
                            received.replaceAll("%challenger%", challenger.getName());
                            challenged.sendMessage(received.addColor());
                        }
                    } else {
                        senders.put(challenger.getUniqueId(), challenged.getUniqueId());
                        receivers.put(challenged.getUniqueId(), challenger.getUniqueId());
                        StringMessage sended = new StringMessage(prefix + messages.getString("duelPetition.sended"));
                        sended.replaceAll("%challenged%", challenged.getName());
                        challenger.sendMessage(sended.addColor());
                        StringMessage received = new StringMessage(prefix + messages.getString("duelPetition.received"));
                        received.replaceAll("%challenger%", challenger.getName());
                        challenged.sendMessage(received.addColor());
                    }
                }
            } else {
                StringMessage noPlayerName = new StringMessage(prefix + messages.getString("duelPetition.noPlayerName"));
                sender.sendMessage(noPlayerName.addColor());
            }
        } else {
            StringMessage senderIsConsole = new StringMessage(prefix + messages.getString("duelPetition.senderIsConsole"));
            sender.sendMessage(senderIsConsole.addColor());
        }
        return true;
    }


    public HashMap<UUID, UUID> getReceivers() {
        return receivers;
    }

    public HashMap<UUID, UUID> getSenders() {
        return senders;
    }
}
