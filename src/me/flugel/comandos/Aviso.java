package me.flugel.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Aviso implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String label,String[] args) {

        if(cmd.getName().equalsIgnoreCase("aviso")){
            if(sender.hasPermission("*")){
                if(args.length == 0){
                    sender.sendMessage("§cUse /aviso <mensagem>");
                }
                if(args.length > 0){
                    String aviso = append(args).replace(sender.getName(), "");
                    for(Player p : Bukkit.getOnlinePlayers()){
                        p.sendTitle("§aAviso", "§2Olhe o chat");
                        p.sendMessage("");
                        p.sendMessage("§bAviso:");
                        p.sendMessage(aviso);
                        p.sendMessage("");
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT,1,1);

                    }
                    return false;
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
