package me.flugel.comandos;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,  Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("echest")){
            if(p.hasPermission("essencial.echest")){
                p.openInventory(p.getEnderChest());
                p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN,1000,1);
                return false;
            }
        }
        return false;
    }
}
