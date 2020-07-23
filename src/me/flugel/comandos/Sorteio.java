package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Sorteio implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        ArrayList<Player> sorteio = new ArrayList<>();

        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage("§c ESSE COMANDO SO PODE SER EXECUTADO POR PLAYERS");
            return true;
        }

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("sorteio")){
            if(p.hasPermission("essencial.sorteio.admin")){
                p.sendMessage("");
                p.sendMessage("§aUse /sorteio iniciar");
                p.sendMessage("");

            }
            if(args.length == 1)
            if(args[0].equalsIgnoreCase("iniciar")){
                if(p.hasPermission("essencial.sorteio.admin")){
                    sortear();
                    return false;
                }else{
                    p.sendMessage("§c Você não tem perm para executar esse comando!");
                    return true;
                }
            }
        }
        return false;
    }
    public static void sortear() {

        FileConfiguration config = Main.getAleatorioTP().aleatorioTPConfig();

        ArrayList<Player> player = new ArrayList<>();
        player.addAll(Bukkit.getOnlinePlayers());

        Player playeraleatorio = player.get(new Random().nextInt(player.size()));
        Bukkit.broadcastMessage("§f-=-=-=-=-=-=§6SORTEIO§f=-=-=-=-=-=-");
        Bukkit.broadcastMessage("§6&lSORTEANDO...");
        Bukkit.broadcastMessage("§f-=-=-=-=-=-=§6SORTEIO§f=-=-=-=-=-=-");

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§f-=-=-=-=-=-=§6SORTEIO§f=-=-=-=-=-=-");
                Bukkit.broadcastMessage("§eJogador que ganhou o sorteio aleatorio!");
                Bukkit.broadcastMessage("§eJogador: §6" + playeraleatorio.getName());
                Bukkit.broadcastMessage("§f-=-=-=-=-=-=§6SORTEIO§f=-=-=-=-=-=-");

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ativarvip" + playeraleatorio.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + playeraleatorio.getName() + " group set vip");
            }
        }.runTaskLater(Main.getInstance(), 10);
    }
}
