package siroswaldo.playerduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.events.DuelPetitionReceivedEvent;
import siroswaldo.playerduels.events.DuelPetitionSendEvent;
import siroswaldo.playerduels.util.message.StringMessage;

public class DuelPetition implements CommandExecutor {

    private final PlayerDuels playerDuels;

    public DuelPetition(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
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
                    if (playerDuels.getPetitions().containsKey(challenged.getUniqueId())){
                        if (playerDuels.getPetitions().get(challenged.getUniqueId()).equals(challenger.getUniqueId())){
                            StringMessage alreadySendThis = new StringMessage(prefix + messages.getString("duelPetition.alreadySendThis"));
                            challenger.sendMessage(alreadySendThis.addColor());
                        } else {
                            DuelPetitionSendEvent duelPetitionSendEvent = new DuelPetitionSendEvent(challenger, challenged);
                            playerDuels.getServer().getPluginManager().callEvent(duelPetitionSendEvent);
                            if (!duelPetitionSendEvent.isCancelled()){
                                StringMessage sended = new StringMessage(prefix + messages.getString("duelPetition.sended"));
                                sended.replaceAll("%challenged%", challenged.getName());
                                challenger.sendMessage(sended.addColor());
                                DuelPetitionReceivedEvent duelPetitionReceivedEvent = new DuelPetitionReceivedEvent(challenger, challenged);
                                playerDuels.getServer().getPluginManager().callEvent(duelPetitionReceivedEvent);
                                if (!duelPetitionReceivedEvent.isCancelled()){
                                    playerDuels.getPetitions().put(challenged.getUniqueId(), challenger.getUniqueId());
                                    StringMessage received = new StringMessage(prefix + messages.getString("duelPetition.received"));
                                    received.replaceAll("%challenger%", challenger.getName());
                                    challenged.sendMessage(received.addColor());
                                }
                            }
                        }
                    } else {
                        DuelPetitionSendEvent duelPetitionSendEvent = new DuelPetitionSendEvent(challenger, challenged);
                        playerDuels.getServer().getPluginManager().callEvent(duelPetitionSendEvent);
                        if (!duelPetitionSendEvent.isCancelled()){
                            StringMessage sended = new StringMessage(prefix + messages.getString("duelPetition.sended"));
                            sended.replaceAll("%challenged%", challenged.getName());
                            challenger.sendMessage(sended.addColor());
                            DuelPetitionReceivedEvent duelPetitionReceivedEvent = new DuelPetitionReceivedEvent(challenger, challenged);
                            playerDuels.getServer().getPluginManager().callEvent(duelPetitionReceivedEvent);
                            if (!duelPetitionReceivedEvent.isCancelled()){
                                playerDuels.getPetitions().put(challenged.getUniqueId(), challenger.getUniqueId());
                                StringMessage received = new StringMessage(prefix + messages.getString("duelPetition.received"));
                                received.replaceAll("%challenger%", challenger.getName());
                                challenged.sendMessage(received.addColor());
                            }
                        }
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
}
