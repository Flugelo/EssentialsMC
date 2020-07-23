package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class tpworld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            if(cmd.getName().equalsIgnoreCase("settp")){
                if(args.length == 1){
                    if(sender.hasPermission("*")){
                        Main.getAleatorioTP().aleatorioTPConfig().set("Cordenadas." + args[0], Main.serialize(((Player) sender).getLocation()));
                        Main.getAleatorioTP().saveConfig();
                        sender.sendMessage("§aSETADA COM SUCESSO!");
                        return false;
                    }else{
                        sender.sendMessage("§cSEM PERMISSÃO");
                    }
                }
            }
            if(cmd.getName().equalsIgnoreCase("survival")){
                teleportarAleatorio(((Player) sender).getPlayer());
                sender.sendMessage("");
                sender.sendMessage("");
                sender.sendMessage("");
                sender.sendMessage("§aTELEPORTADO PARA SOBREVIVÊNCIA");
                sender.sendMessage("§cSAIA DO SPAWN PARA PODER CONSTRUIR!");
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                return false;
            }

        }

        return false;
    }
    public static void teleportarAleatorio(Player p) {

        FileConfiguration config = Main.getAleatorioTP().aleatorioTPConfig();

        ArrayList<Location> loc = new ArrayList<>();

        for(String locs : config.getConfigurationSection("Cordenadas").getKeys(false)) {
            loc.add(Main.unserialize(config.getString("Cordenadas." + locs)));
        }

        Location localAleatorio = loc.get(new Random().nextInt(loc.size()));

        p.teleport(localAleatorio);

    }
}
