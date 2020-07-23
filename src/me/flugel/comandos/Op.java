package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Op implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof  Player)){
            if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                target.setOp(true);
                sender.sendMessage("§cOP SETADO COM SUCESSO");
                return false;
            }
        }
        String tag = Main.getInstance().preffix;
        String semPerm = Main.getInstance().semPerm;

        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("op")){
            if(args.length == 0){
                if(p.hasPermission("essencial.op")){
                    p.sendMessage("");
                    p.sendMessage(tag+"§6Use /op <jogador> <senha>");
                    p.sendMessage("");
                    return true;
                }else{
                    p.sendMessage(tag+semPerm);
                }
            }
            if(args.length == 2){
                if(p.hasPermission("essencial.op")){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        String senha = args[1];
                        String senhaCorreta = Main.getInstance().getConfig().getString("senhaOp");
                        String senhaIncorreta = Main.getInstance().getConfig().getString("senhaErrada").replace("&", "§");
                        if(senha.equals(senhaCorreta)){
                            target.setOp(true);
                            target.sendMessage(tag+"§aVocê agora tem permissão de §cOP");
                            target.playSound(target.getLocation(), Sound.ITEM_TOTEM_USE,1,1);
                            p.sendMessage(tag+"§cVocê setou OP no jogador §2" + target.getName());
                            return false;
                        }else{
                            p.sendMessage(tag+ senhaIncorreta);
                            return true;
                        }
                    }else{
                        p.sendMessage(tag+"§cEsse jogador não existe ou não esta online no momento.");
                        return true;
                    }
                }else{
                    p.sendMessage(tag+semPerm);
                    return true;
                }
            }
        }
        return false;
    }
}
