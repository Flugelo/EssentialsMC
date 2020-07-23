package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String tag = Main.getInstance().preffix;
        String semPerm = Main.getInstance().semPerm;
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage("§cESSE COMANDO SO PODE SER EXECUTADOR POR UM PLAYER!");
            return true;
        }
         return false;
    }
}
