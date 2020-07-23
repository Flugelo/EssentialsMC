package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    String tag = Main.getInstance().preffix;
    String semPerm = Main.getInstance().semPerm;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("gm")){
            if(p.hasPermission("essencial.gm")){
                p.sendMessage("");
                p.sendMessage("§e>> §6Criativo: §c/gmc");
                p.sendMessage("");
                p.sendMessage("§e>> §6Survival: §c/gms");
                p.sendMessage("");
                p.sendMessage("§e>> §6Espectador: §c/gme");
                p.sendMessage("");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
                return true;
            }else{
                p.sendMessage(tag+semPerm);
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
        }
        if(cmd.getName().equalsIgnoreCase("gmc")){
            if(p.hasPermission("essencial.gm")){
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(tag+"§aSeu modo de jogo foi auterado para CRIATIVO");
                return false;
            }else{
                p.sendMessage(tag+semPerm);
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
        }
        if(cmd.getName().equalsIgnoreCase("gms")){
            if(p.hasPermission("essencial.gm")){
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(tag+"§aSeu modo de jogo foi auterado para SURVIVAL");
                return false;
            }else{
                p.sendMessage(tag+semPerm);
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
        }
        if(cmd.getName().equalsIgnoreCase("gme")){
            if(p.hasPermission("essencial.gm")){
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(tag+"§aSeu modo de jogo foi auterado para ESPECTADOR");
                return false;
            }else{
                p.sendMessage(tag+semPerm);
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
        }
        return false;
    }
}
