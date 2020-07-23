package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class Kick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String tag = Main.getInstance().preffix;

        String semPerm = Main.getInstance().semPerm;
        String noKick = Main.getInstance().noKick;
        if(cmd.getName().equalsIgnoreCase("kick")){
            if(sender.hasPermission("essencial.kick")){
                if(args.length == 0){
                    sender.sendMessage("");
                    sender.sendMessage(tag+"§cUse /kick <jogador> <motivo>");
                    sender.sendMessage("");
                    return true;
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        if(!(target.getName().equals(sender.getName()))){
                            ArrayList<String> nullKick = new ArrayList<>();
                            nullKick.addAll((Collection) Main.getInstance().getConfig().getList("ConfigKick.nullKick"));
                            Bukkit.getConsoleSender().sendMessage(String.valueOf(nullKick));
                            target.kickPlayer(nullKick.toString().replace("," , "\n").replace("[", "").replace("]", "")
                                    .replace("&","§").replace("(sender)", sender.getName()));
                            return false;
                        }else{
                            sender.sendMessage(tag+noKick);
                            return true;
                        }
                    }else{
                        sender.sendMessage(tag+"§cJogador não existe ou não esta online!");
                        return true;
                    }
                }
                if(args.length >= 2){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    String motivo = append(args).replace(target.getName(), "");
                    if(target != null){
                        if(!(target.getName().equals(sender.getName()))){
                            String estilo = Main.getInstance().getConfig().getString("ConfigKick.mensagemKick").replace("&", "§").
                                    replace("(playertarget)", target.getName()).
                                    replace("(motivo)", motivo).
                                    replace("(sender)", sender.getName());

                            ArrayList<String> formato = new ArrayList<>();
                            formato.addAll((Collection) Main.getInstance().getConfig().getList("ConfigKick.mensagemKick"));
                            target.kickPlayer(formato.toString().replace("," , "\n").replace("[", "").replace("]", "")
                                    .replace("&","§").replace("(sender)", sender.getName()).replace("(motivo)", motivo));

                            String allMens = Main.getInstance().getConfig().getString("ConfigKick.AllMenKick").replace("&", "§").
                                    replace("(playertarget)", target.getName()).
                                    replace("(motivo)", motivo).
                                    replace("(sender)", sender.getName());
                            Bukkit.broadcastMessage(tag+allMens);
                            return true;
                        }else{
                            sender.sendMessage(tag + noKick);
                            return true;
                        }
                    }else{
                        sender.sendMessage(tag+"§cJogador não existe ou não esta online!");
                        return true;
                    }
                }
            }else {
                sender.sendMessage(tag+semPerm);
                return true;
            }
        }

        return false;
    }

    public static String append(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < args.length; x++) {
            stringBuilder.append(String.valueOf(args[x]) + " ");
        }
        return stringBuilder.toString();
    }
}
