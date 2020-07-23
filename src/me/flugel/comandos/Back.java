package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;

public class Back implements CommandExecutor {

    HashMap<Player, Long> cw = Main.getInstance().cw;

    String tag = Main.getInstance().preffix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        HashMap<Player, Location> back = Main.getInstance().back;


        if(!(sender instanceof Player)){
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("voltar")){
            if(p.hasPermission("essencial.voltar.vip")){
                if(back.containsKey(p)){
                    p.teleport(back.get(p));
                    back.remove(p);
                    p.sendMessage("");
                    p.sendMessage("");
                    p.sendMessage(tag+"§aVocê volto onde morreu!");
                    return false;
                }else{
                    p.sendMessage("§cVocê não teleporte para ultima morte!");
                    return true;
                }
            }
        }
        return false;
    }
}
