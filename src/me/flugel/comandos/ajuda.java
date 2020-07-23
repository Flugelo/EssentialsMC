package me.flugel.comandos;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ajuda implements CommandExecutor, Listener {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String tag = Main.getInstance().preffix;

        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("ajuda")){
            p.openInventory(inventario());
            return false;
        }

        if(cmd.getName().equalsIgnoreCase("tickets")){
            if(p.hasPermission("essencial.tickets")){
                p.openInventory(listTickts());
                return false;
            }else{
                p.sendMessage(tag+"§cSem permissão!");
                return true;
            }
        }
        if(cmd.getName().equalsIgnoreCase("ajudaClaim")){
            p.sendMessage("");
            p.sendMessage("§cAssista esse video que você saberar como proteger sua casa!");
            p.sendMessage("§e https://youtu.be/AfbKiYjogBI");
            p.sendMessage("");
            p.playSound(p.getLocation(),Sound.ENTITY_VILLAGER_CELEBRATE,1,1);
            return false;
        }



        return false;
    }
    public static Inventory inventario(){
        Inventory inv = Bukkit.createInventory(null, 3*9, "§6§lQual sua duvida?");
        ItemStack ajuda1 = new ItemStack(Material.LIME_CONCRETE);
        ItemStack ajuda2 = new ItemStack(Material.RED_CONCRETE);
        ItemMeta ajuda1Meta = ajuda1.getItemMeta();
        ajuda1Meta.setDisplayName("§2Duvidas simples!");

        ItemMeta ajuda2Meta = ajuda2.getItemMeta();
        ajuda2Meta.setDisplayName("§eCriar ticket!");

        ArrayList<String> ajuda1Lore = new ArrayList<>();

        ajuda1Lore.add("");
        ajuda1Lore.add("§f°§aPara ver quais itens tem para vender digite §2/vender");
        ajuda1Lore.add("§f°§aQuanto §2mais §avoce joga §2mais §aarea você pode claimar");
        ajuda1Lore.add("§f°§aPara você vender itens para outros player usa §2/mercado");
        ajuda1Lore.add("§f°§aNão sabe como proteger sua casa? use §2/ajudaClaim");
        ajuda1Lore.add("§f°§aPara ser staff utilize §2/aplicar §apara saber se precisamos de staff");
        ajuda1Lore.add("§f°§aAlguem esta sendo toxico? abuso verbal, discurso de odio");
        ajuda1Lore.add("§a ou ate mesmo usando cheating? use §2/reportar");
        ajuda1Lore.add("");

        ajuda1Meta.setLore(ajuda1Lore);
        ajuda1.setItemMeta(ajuda1Meta);

        ArrayList<String> ajuda2Lore = new ArrayList<>();


        ajuda2Lore.add("");
        ajuda2Lore.add("§6Nem das ajudas anteriores te ajudou?");
        ajuda2Lore.add("§6Clique aqui para criar um ticket!");
        ajuda1Lore.add("§6Entraremos em contato mais rapido possivel!");
        ajuda2Lore.add("");

        ajuda2Meta.setLore(ajuda2Lore);
        ajuda2.setItemMeta(ajuda2Meta);

        inv.setItem(10, ajuda1);
        inv.setItem(16, ajuda2);

        return inv;
    }
    public static Inventory listTickts(){

        Inventory inv = Bukkit.createInventory(null, 9*6, "§b§lLista de tickets");

        for(String p : Main.getTicket().ticketsConfig().getConfigurationSection("Tickets").getKeys(false)){
            ItemStack item1 = new ItemStack(Material.PLAYER_HEAD);
            ItemMeta itemMeta = item1.getItemMeta();

            itemMeta.setDisplayName(p);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§cClique para mandar uma mensagem automática ao player");
            lore.add("§c/tell player &6Ola, aceitamos seu ticket! em oque posso te ajudar?");
            lore.add("");
            itemMeta.setLore(lore);

            item1.setItemMeta(itemMeta);

            inv.addItem(item1);
        }
        return inv;
    }

    @EventHandler
    public static void aoClicar(InventoryClickEvent e){
        HashMap<Player, String> ticket = Main.getInstance().ticket;
        String tag = Main.getInstance().preffix;
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase("§6§lQual sua duvida?")){
            e.setCancelled(true);
            if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCriar ticket!")){
                    if(!(ticket.containsKey(p))){
                        ticket.put(p, "ticket");
                        p.sendMessage(tag+"§aSeu ticket foi enviado com sucesso! Logo entraremos em contato");
                        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE,1,1);
                        for(Player staff : Bukkit.getOnlinePlayers()){
                            if(staff.hasPermission("essencial.ticket")){
                                staff.sendTitle("§aNOVO TICKET", "§6Use /tickets para ver");
                                staff.sendMessage(tag+"§6Um ticket foi criado, digite /tickets");
                                staff.playSound(p.getLocation(), Sound.BLOCK_BELL_USE,1,1);
                                Main.getTicket().ticketsConfig().createSection("Tickets." + p.getName());
                                Main.getTicket().saveConfig();
                            }
                        }
                    }else{
                        p.sendMessage(tag+"§cVocê já tem um §4TICKET §cfeito!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    }

                }
            }
        }
    }
    @EventHandler
    public static void aoClicarTicket(InventoryClickEvent e){
        HashMap<Player, String> ticket = Main.getInstance().ticket;
        Player staff = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase("§b§lLista de tickets")){
            e.setCancelled(true);
            if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR){
                if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){

                    Player target = Bukkit.getPlayerExact(e.getCurrentItem().getItemMeta().getDisplayName());
                    if(target == null){
                        ((Player) e.getWhoClicked()).getPlayer().sendMessage("§cEsse jogador não esta online no momento!");
                        ((Player) e.getWhoClicked()).getPlayer().playSound(((Player) e.getWhoClicked()).getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        return;
                    }
                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(target.getName())) {
                            staff.chat("/tell " + target.getName() + " &6Ola, aceitamos seu ticket! em oque posso te ajudar?");
                            staff.closeInventory();
                            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
                            for (int i = 0; i < 5; i++) {
                                target.sendMessage("");
                            }
                            if (ticket.containsKey(target)) {
                                ticket.remove(target);
                            }
                            if (Main.getTicket().ticketsConfig().getConfigurationSection("Tickets").contains(target.getName())) {
                                Main.getTicket().ticketsConfig().set("Tickets." + target.getName(), null);
                                Main.getTicket().saveConfig();
                            } else {
                                staff.closeInventory();
                                staff.sendMessage("§cEsse ticket não existe mais!");
                                staff.playSound(staff.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                            }
                        }
                }
            }
        }
    }



}
