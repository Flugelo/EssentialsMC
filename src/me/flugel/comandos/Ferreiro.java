package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ferreiro implements CommandExecutor {

    String tag = Main.getInstance().preffix;
    String semPerm = Main.getInstance().semPerm;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("ferreiro")){
            if(p.hasPermission("essencial.ferreiro")){
                p.sendMessage("");
                p.sendMessage(tag+"§6Use /ferreiro setar");
                p.sendMessage("§f§l° §7para setar o spawn do Ferreiro");
                p.sendMessage(tag+"§6Use /ferreiro remover ");
                p.sendMessage("§f§l° §7para remover o spawn do Ferreiro");
                p.sendMessage("");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 0);
                return true;
            }
            if (args[0].equalsIgnoreCase("setar")){
                if(p.hasPermission("essencial.ferreiro")){
                }

            }
            if (args[0].equalsIgnoreCase("remover")){
                if(p.hasPermission("essencial.ferreiro")){

                }

            }
        }

        return false;
    }
}
