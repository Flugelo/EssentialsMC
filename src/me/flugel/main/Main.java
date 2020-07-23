package me.flugel.main;

import me.flugel.comandos.*;
import me.flugel.eventos.AoMorrer;
import me.flugel.eventos.Banido;
import me.flugel.eventos.Entrar;
import me.flugel.file.AleatorioTP;
import me.flugel.file.Banidos;
import me.flugel.file.Tickets;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static Main instance;

    public HashMap<Player, Player> tpa = new HashMap<Player, Player>();

    public HashMap<Player, String> ticket = new HashMap<>();

    public HashMap<Player, Location> back = new HashMap<>();

    public static HashMap<Player, Long> cw = new HashMap<>();

    public static ArrayList<Player> admin = new ArrayList<>();

    private static Ferreiro ferreiro;

    private static AleatorioTP aleatorioTP;

    private static Tickets tickets;

    private static Banidos banidos;




    private static final Logger log = Logger.getLogger("Minecraft");

    private static Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        comandos();
        eventos();
        ferreiro = new Ferreiro();
        aleatorioTP = new AleatorioTP();
        tickets = new Tickets();
        banidos = new Banidos();


        if (!setupEconomy()) {
            log.severe(String.format("[%s] - H_ScoreBoard desabilitado por falta do plugin vault!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

    public String preffix = getConfig().getString("Config.preffix").replace("&", "§");
    public String semPerm = getConfig().getString("Config.semPermissao").replace("&", "§");
    public String noKick = getConfig().getString("ConfigKick.noKick").replace("&", "§");

    @Override
    public void onDisable() {

    }

    private void comandos(){
        getCommand("gm").setExecutor(new Gamemode());
        getCommand("gmc").setExecutor(new Gamemode());
        getCommand("gms").setExecutor(new Gamemode());
        getCommand("gme").setExecutor(new Gamemode());
        getCommand("fly").setExecutor(new Fly());
        getCommand("kick").setExecutor(new Kick());
        getCommand("speed").setExecutor(new Speed());
        getCommand("op").setExecutor(new Op());
        getCommand("settp").setExecutor(new tpworld());
        getCommand("survival").setExecutor(new tpworld());
        getCommand("tpa").setExecutor(new Tpa());
        getCommand("tpaccept").setExecutor(new Tpa());
        getCommand("sorteio").setExecutor(new Sorteio());
        getCommand("luz").setExecutor(new Luz());
        getCommand("inv").setExecutor(new Inv());
        getCommand("ajuda").setExecutor(new ajuda());
        getCommand("tickets").setExecutor(new ajuda());
        getCommand("ajudaClaim").setExecutor(new ajuda());
        getCommand("voltar").setExecutor(new Back());
        getCommand("ban").setExecutor(new Ban());
        getCommand("unban").setExecutor(new Ban());
        getCommand("echest").setExecutor(new EnderChest());
        getCommand("adm").setExecutor(new Admin());
        getCommand("aviso").setExecutor(new Aviso());

    }

    private void eventos(){
        Bukkit.getPluginManager().registerEvents(new Entrar(), this);
        Bukkit.getPluginManager().registerEvents(new ajuda(), this);
        Bukkit.getPluginManager().registerEvents(new AoMorrer(), this);
        Bukkit.getPluginManager().registerEvents(new Banido(), this);
    }

    public static Main getInstance(){
        return instance;
    }


    public static String serialize(Location loc) {
        return loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
    }

    public static Location unserialize(String location) {
        String[] parts = location.split(":");

        org.bukkit.World w = Bukkit.getServer().getWorld(parts[0]);
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);
        double z = Double.parseDouble(parts[3]);
        float yaw = Float.parseFloat(parts[4]);
        float pitch = Float.parseFloat(parts[5]);
        return new Location((org.bukkit.World) w, x, y, z, yaw, pitch);
    }

    public static Ferreiro getFerreiro() {
        return ferreiro;
    }
    public static AleatorioTP getAleatorioTP(){
        return aleatorioTP;
    }
    public static Tickets getTicket() {
        return tickets;
    }
    public static Economy getEconomy() {
        return econ;
    }
    public static Banidos getBanidos(){
        return banidos;
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
