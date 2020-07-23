package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Xray implements CommandExecutor {

    String tag = Main.getInstance().preffix;
    String semPerm = Main.getInstance().semPerm;

    @Override
    public boolean onCommand( CommandSender sender,  Command cmd, String label,  String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("xray")){
            if(p.hasPermission("essencial.xray")){
                switch (args.length){
                    case 0:
                        p.sendMessage("");
                        p.sendMessage(tag+"§cUse §4/xray <Player>");
                        p.sendMessage("");
                        break;
                    case 1:
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                        }else{
                            p.sendMessage(tag+"§cJogador não existe ou não esta online!");
                        }
                }
            }
        }

        return false;
    }
}
