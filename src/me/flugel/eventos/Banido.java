package me.flugel.eventos;

import me.flugel.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class Banido implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
        if (e.getLoginResult() == AsyncPlayerPreLoginEvent.Result.KICK_OTHER) return;
        if (Main.getBanidos().banidosConfig().getConfigurationSection("banidos").contains(e.getName())) {
            String motivo = Main.getBanidos().banidosConfig().getString("banidos." + e.getName());
            String staff = Main.getBanidos().banidosConfig().getString("banidos." + e.getName());
            String[] parts = staff.split(":");
            String nome = parts[1];
            String motivoP = parts[0];
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, " §4§l▁▂▃▅▆▇█▉ BANIDO ██▇▆▅▄▃▂▁\n §cVocê foi banido por:\n §4" + motivoP + "\n§6Pelo staff: §e"+ nome + "\n "+"\n§chttp://mortalcraftsurvival.minemarket.com.br/" +"\n §4§l▁▂▃▅▆▇█▉ BANIDO ██▇▆▅▄▃▂▁");
        }
        return;
    }
}
