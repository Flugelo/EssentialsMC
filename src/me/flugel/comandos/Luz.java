package me.flugel.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Luz implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("§cESSE COMANDO SO PODE SER EXECUTADO POR PLAYERS");
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("luz")){
            if(!(p.hasPotionEffect(PotionEffectType.NIGHT_VISION))){
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1,2);
                p.sendTitle("§f§lLUZ", "§eLigada");
                p.addPotionEffect (new PotionEffect (PotionEffectType.NIGHT_VISION, 100000000, 1));
                return false;
            }else{
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1,2);
                p.sendTitle("§f§lLUZ", "§eDesligada");
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                return false;
            }
        }
        return false;
    }

}
