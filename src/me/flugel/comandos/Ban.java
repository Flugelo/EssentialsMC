package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if(cmd.getName().equalsIgnoreCase("ban")){
            if(sender.hasPermission("essencial.ban")){
                if(args.length == 0){
                    sender.sendMessage("");
                    sender.sendMessage("§cUse §4/ban <player> <razão>");
                    sender.sendMessage("");
                    return true;
                }
                if(args.length > 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    String motivo = append(args).replace(sender.getName(), "").replace(target.getName(), "");
                    if(!(Main.getInstance().getBanidos().banidosConfig().getConfigurationSection("banidos").contains(target.getName()))){
                        Main.getBanidos().banidosConfig().set("banidos."+ target.getName(), motivo + ":" + sender.getName());
                        Main.getBanidos().saveConfig();
                        target.kickPlayer("§4§l▁▂▃▅▆▇█▉ BANIDO ██▇▆▅▄▃▂▁\n§6Motivo: §e"+ motivo +"\n§6Panido pelo: §e" + sender.getName()+ "\n§4§l▁▂▃▅▆▇█▉ BANIDO ██▇▆▅▄▃▂▁");
                        for(Player online : Bukkit.getOnlinePlayers()){
                            online.sendMessage("");
                            online.sendMessage("");
                            online.sendMessage("    §4§l▁▂▃▅▆▇█▉ BANIDO ██▇▆▅▄▃▂▁");
                            online.sendMessage("       §6Jogador: §e" + target.getName());
                            online.sendMessage("       §6Motivo: §e"+ motivo);
                            online.sendMessage("       §6Tempo do ban: §ePERMANENTE!");
                            online.sendMessage("       §6Panido pelo: §e" + sender.getName());
                            online.sendMessage("    §4§l▁▂▃▅▆▇█▉ BANIDO ██▇▆▅▄▃▂▁");
                            online.playSound(online.getLocation(), Sound.EVENT_RAID_HORN, 1000 ,1);
                        }
                        return false;
                    }else{
                        sender.sendMessage("§cEsse jogador ja esta banido");
                        return true;
                    }
                }
            }else{
                sender.sendMessage("§cSem Permissão!");
                return false;
            }
        }

        if(cmd.getName().equalsIgnoreCase("unban")){
            if(sender.hasPermission("essencial.unban")){
                if(args.length == 0){
                    sender.sendMessage("");
                    sender.sendMessage("§cUse §4/unban <player>");
                    sender.sendMessage("");
                }

                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(Main.getBanidos().banidosConfig().getConfigurationSection("banidos").contains(args[0])){
                        Main.getBanidos().banidosConfig().set("banidos." + args[0], null);
                        Main.getBanidos().saveConfig();
                        sender.sendMessage("§cJoador desbanido");
                    }else{
                        sender.sendMessage("§cEsse jogador não esta banido!");
                        return true;
                    }
                }
            }else{
                sender.sendMessage("§cSem permissão!");
            }
        }
        return false;
    }

    public static String append(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < args.length; x++) {
            stringBuilder.append(String.valueOf(args[x]) + " ");
        }
        return stringBuilder.toString();
    }
}
