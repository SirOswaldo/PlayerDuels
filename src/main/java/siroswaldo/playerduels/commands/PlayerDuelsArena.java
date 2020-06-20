package siroswaldo.playerduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import siroswaldo.playerduels.PlayerDuels;
import siroswaldo.playerduels.arena.Arena;
import siroswaldo.playerduels.inventories.arenaditor.ArenaEditorInventory;
import siroswaldo.playerduels.util.message.ListMessage;
import siroswaldo.playerduels.util.message.StringMessage;

import java.util.ArrayList;

public class PlayerDuelsArena implements CommandExecutor {

    private final PlayerDuels playerDuels;

    public PlayerDuelsArena(PlayerDuels playerDuels) {
        this.playerDuels = playerDuels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = playerDuels.getMessages().getFileConfiguration();
        String prefix = messages.getString("prefix");
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length > 0){
                switch (args[0].toLowerCase()){
                    case "list":
                        ListMessage names = new ListMessage(new ArrayList<>(playerDuels.getArenas().keySet()));
                        names.addColor();
                        names.sendMessage(player);
                    case "create":
                        if (args.length > 1){
                            if (!playerDuels.getArenas().containsKey(args[1])){
                                Arena arena = new Arena(args[1], player.getLocation(), player.getLocation(), player.getLocation());
                                playerDuels.getArenas().put(args[1], arena);
                                playerDuels.saveArena(arena);
                                StringMessage createComplete = new StringMessage(prefix + messages.getString("playerDuelsArena.createComplete"));
                                createComplete.replaceAll("%arena%", args[1]);
                                player.sendMessage(createComplete.addColor());
                            } else {
                                StringMessage createNameAlreadyInUse = new StringMessage(prefix + messages.getString("playerDuelsArena.createNameAlreadyInUse"));
                                player.sendMessage(createNameAlreadyInUse.addColor());
                            }
                        } else {
                            StringMessage createNoName = new StringMessage(prefix + messages.getString("playerDuelsArena.createNoName"));
                            player.sendMessage(createNoName.addColor());
                        }
                    case "edit":
                        if (args.length > 1){
                            if (playerDuels.getArenas().containsKey(args[1])){
                                Arena arena = playerDuels.getArenas().get(args[1]);
                                if (arena.getStatus().equals("WAITING")){
                                    arena.setStatus("EDITING");
                                    ArenaEditorInventory arenaEditorInventory = new ArenaEditorInventory(playerDuels, player, arena);
                                    arenaEditorInventory.openInventory();
                                } else {
                                    StringMessage editInUseArena = new StringMessage(prefix +messages.getString("playerDuelsArena.editInUseArena"));
                                    player.sendMessage(editInUseArena.addColor());
                                }
                            } else {
                                StringMessage editInvalidName = new StringMessage(prefix + messages.getString("playerDuelsArena.editInvalidName"));
                                player.sendMessage(editInvalidName.addColor());
                            }
                        } else {
                            StringMessage editNoName = new StringMessage(prefix + messages.getString("playerDuelsArena.editNoName"));
                            player.sendMessage(editNoName.addColor());
                        }
                    case "help":
                        ListMessage help = new ListMessage();
                        help.addLine("&6&m>>--------&6|&b&lPlayerDuels&6|&6&m--------<<");
                        help.addLine("&e/playerduelsarena list");
                        help.addLine("&e/playerduelsarena create [name]");
                        help.addLine("&e/playerduelsarena edit [name]");
                        help.addLine("&6&m>>------------------------------<<");
                        help.addColor();
                        help.sendMessage(player);
                    default:
                        StringMessage invalidArgument = new StringMessage(prefix + messages.getString("playerDuelsArena.invalidArgument"));
                        player.sendMessage(invalidArgument.addColor());
                }
            } else {
                StringMessage noArgument = new StringMessage(prefix + messages.getString("playerDuelsArena.noArgument"));
                player.sendMessage(noArgument.addColor());
            }
        } else {
            StringMessage senderIsConsole = new StringMessage(prefix +messages.getString("playerDuelsArena.senderIsConsole"));
            sender.sendMessage(senderIsConsole.addColor());
        }
        return true;
    }
}
