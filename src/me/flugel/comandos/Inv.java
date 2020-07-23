package me.flugel.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inv implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("inv")){
            if(p.hasPermission("essencial.inv")){
                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        p.openInventory(target.getInventory());
                    }else{
                        p.sendMessage("§cPlayer offline ou não existe!");
                        return true;
                    }
                }
            }else{
                p.sendMessage("§cSem permissão");
                return true;
            }
        }

        return false;
    }
}
