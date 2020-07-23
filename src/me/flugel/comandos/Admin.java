package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Admin implements CommandExecutor {

    String tag = Main.getInstance().preffix;
    String semPerm = Main.getInstance().semPerm;

    public static HashMap<Player, ItemStack[]> items = new HashMap<>();
    public static HashMap<Player, ItemStack[]> armor = new HashMap<>();

    @Override
    public boolean onCommand( CommandSender sender,  Command cmd,  String label,  String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("adm")){
            if(p.hasPermission("essencial.admin")){
                if(Main.admin.contains(p)){
                    for(Player a : Bukkit.getOnlinePlayers()){
                        if(!p.equals(a))
                            a.showPlayer(p);
                    }
                    p.getInventory().setContents(items.get(p));
                    p.getInventory().setArmorContents(armor.get(p));
                    items.remove(p);
                    Main.admin.remove(p);
                    p.sendTitle("§c§lADMIN", "§4Desligado");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
                    p.setGameMode(GameMode.CREATIVE);
                }else{
                    Main.admin.add(p);
                    items.put(p, p.getInventory().getContents());
                    armor.put(p,p.getInventory().getArmorContents());
                    for(Player a : Bukkit.getOnlinePlayers()){
                        if(!a.equals(p))
                        a.hidePlayer(p);
                    }
                    p.sendTitle("§c§lADMIN", "§aLigado");
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE,1,1);
                    p.setGameMode(GameMode.SPECTATOR);
                }
            }else{
                p.sendMessage(tag+semPerm);
            }
        }

        return false;
    }
}
