package me.flugel.eventos;

import me.flugel.main.Main;
import net.minecraft.server.v1_16_R1.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Entrar implements Listener {


@EventHandler
    public static void joinServer(PlayerJoinEvent e){
        ArrayList<Player> staff = Main.admin;
        Player p = e.getPlayer();
        String linhaUm = Main.getInstance().getConfig().getString("Config.1linha").replace("&", "§").replace("(player)", p.getName());
        String linhaDois = Main.getInstance().getConfig().getString("Config.2linha").replace("&", "§").replace("(player)", p.getName());
        String aoEntrar = Main.getInstance().getConfig().getString("Config.MensagemJoin").replace("&", "§").replace("(player)", p.getName());
        if(p.hasPermission("essencial.join.adm")){
            new BukkitRunnable(){
                @Override
                public void run() {
                    p.sendTitle("§6§lBem Vindo", "§c&lAdministrador");
                    p.chat("/adm");

                }
            }.runTaskLater(Main.getInstance(), 2);
            e.setJoinMessage("");
            return;
        }
        p.sendTitle(linhaUm, linhaDois);
        e.setJoinMessage(aoEntrar);


        if(staff.size() > 0){
            for(Player x : staff){
                p.hidePlayer(x);
            }
        }

    ArrayList bemVindo = new ArrayList();
    bemVindo.addAll(Main.getInstance().getConfig().getList("Config.mensagem"));

        p.sendMessage(bemVindo.toString().replace("&", "§").replace("(player)", p.getName()).replace("," , "\n").replace("]", "").replace("[", ""));

    }
    @EventHandler
    public static void exitServer (PlayerQuitEvent e){
        Player p = e.getPlayer();
        String aoSair = Main.getInstance().getConfig().getString("Config.MensagemExit").replace("&", "§").replace("(player)", e.getPlayer().getName());
        if(p.hasPermission("essencial.join.adm")){
            e.setQuitMessage("");
            return;
        }
        e.setQuitMessage(aoSair);
    }
    
}
