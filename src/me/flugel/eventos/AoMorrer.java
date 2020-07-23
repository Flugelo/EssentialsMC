package me.flugel.eventos;

import me.flugel.main.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;

public class AoMorrer implements Listener {


    @EventHandler
    public static void aoMorrer(PlayerDeathEvent e){
        String tag = Main.getInstance().preffix;
        HashMap<Player, Location> back = Main.getInstance().back;
        e.getEntity().getPlayer().getLocation();
        if(back.containsKey(e.getEntity().getPlayer())){
                e.getEntity().getPlayer().sendMessage("");
                e.getEntity().getPlayer().sendMessage("");
                e.getEntity().getPlayer().sendMessage("");
                e.getEntity().getPlayer().sendMessage("");
                e.getEntity().getPlayer().sendMessage("");
                e.getEntity().getPlayer().sendMessage(tag+"§cUse §4/voltar para teleportar onde você morreu! ");
                back.replace(e.getEntity().getPlayer(), e.getEntity().getPlayer().getLocation());
        }else{
            back.put(e.getEntity().getPlayer(), e.getEntity().getPlayer().getLocation());
            e.getEntity().getPlayer().sendMessage("");
            e.getEntity().getPlayer().sendMessage("");
            e.getEntity().getPlayer().sendMessage("");
            e.getEntity().getPlayer().sendMessage("");
            e.getEntity().getPlayer().sendMessage("");
            e.getEntity().getPlayer().sendMessage(tag+ "§cUse §4/voltar para teleportar onde você morreu! ");
        }
    }
}
