package me.flugel.comandos;

import me.flugel.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tpa implements CommandExecutor {

    public Player target;
    HashMap<Player, Player> tpa = Main.getInstance().tpa;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String semPerm = Main.getInstance().semPerm;
        String tag = Main.getInstance().preffix;

        ArrayList<String> peneira = new ArrayList<>();
        int tempo = Main.getInstance().getConfig().getInt("CofigTpa.tempo");
        String peneiraTempo = String.valueOf(tempo);
        peneira.addAll((Collection<? extends String>) Main.getInstance().getConfig().getList("CofigTpa.tpaTarget"));
        String mensagem = peneira.toString().replace("," , "\n").replace("[", "").replace("]", "")
                .replace("&","§").replace("(sender)", sender.getName()).replace("(tempo)", peneiraTempo);
        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(tag+"§cEsse comando so pode ser executado por jogadores");
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("tpa")){
            if(p.hasPermission("Essencial.tpa"))
            if(args.length == 0){
                p.sendMessage("");
                p.sendMessage(tag+ "§6Use /tpa <jogador>");
                p.sendMessage("");
                return true;
            }
            if(args.length == 1){
                 target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    if(p.equals(target)){
                        p.sendMessage(tag+"§cVocê não pode mandar TPA para você mesmo!");
                        return true;
                    }
                    tpa.put(target, p);
                    String tpaPedido = Main.getInstance().getConfig().getString("CofigTpa.tpaPedido").replace("&", "§").replace("(playertarget)",target.getName());
                    p.sendMessage(tag+tpaPedido);
                    target.sendMessage(mensagem);
                    new BukkitRunnable(){

                        @Override
                        public void run() {
                            if(tpa.containsKey(target)){
                                tpa.remove(target);
                            }
                        }
                    }.runTaskLater(Main.getInstance(), 20*60);
                    return  true;
                }else{
                    p.sendMessage(tag+"§cjogador não existe ou não esta online no momento!");
                    return true;
                }
            }
        }
        if(cmd.getName().equalsIgnoreCase("tpaccept")){
            if(p.hasPermission("Essencial.tpa")){
                for(Player h : Bukkit.getOnlinePlayers()){
                    if(tpa.containsKey(p)){
                        if(tpa.get(p).equals(h)){
                            h.teleport(p);
                            h.sendMessage(tag+"§aSeu pedido de teleporte foi aceito!");
                            tpa.remove(h);
                            tpa.remove(p);
                            return false;
                        }
                    }
                }
                    p.sendMessage(tag+"§cNão contem pedidos de teleporte para você");
                    return true;
            }else{
                p.sendMessage(tag+semPerm);
                return true;
            }
        }
        if(cmd.getName().equalsIgnoreCase("tpadeny")){
            if(p.hasPermission("Essencial.tpa")){
                for(Player h : Bukkit.getOnlinePlayers()){
                    if(tpa.containsKey(h)){
                        if(tpa.get(h).equals(p)){
                            tpa.remove(h);
                            p.sendMessage(tag+"§cVocê recusou o tpa!");
                            h.sendMessage(tag+"§cSeu Tpa não foi aceito!");
                            return false;
                        }
                    }
                }
                p.sendMessage(tag+"§cNão contem TPA");
                return true;

            }else{
                p.sendMessage(tag+semPerm);
                return true;
            }
        }
        return false;
    }

}
