package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    String tag = Main.getInstance().preffix;
    String semPerm = Main.getInstance().semPerm;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage("§cESSE COMANDO SO PODE SER EXECUTADO POR PLAYERS");
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("fly")){
            if(p.hasPermission("essencial.fly")){
                if(p.getAllowFlight() == false){
                    p.setAllowFlight(true);
                    p.sendMessage("");
                    p.sendMessage(tag+"§aSeu fly foi ATIVADO!");
                    p.sendMessage("");
                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE,1,1);
                    return false;
                }else{
                    p.setAllowFlight(false);
                    p.sendMessage("");
                    p.sendMessage(tag+"§aSeu fly foi DESATIVADO!");
                    p.sendMessage("");
                    p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE,1,1);
                    return true;
                }

            }else{
                p.sendMessage(tag+semPerm);
            }
        }
        return false;
    }
}
