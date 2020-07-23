
package me.flugel.file;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import me.flugel.main.Main;
public class Tickets {
    Main config = Main.getInstance();
    private File tickets;
    private FileConfiguration fileConfiguration;

    public Tickets() {

        tickets = new File(config.getDataFolder(), "tickets.yml");
        fileConfiguration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(tickets);
        if (!(tickets.exists())) {
            try {
                tickets.createNewFile();
                Bukkit.getConsoleSender().sendMessage(" §aLocal de armazenamento [tickets.yml] foi criado com sucesso!");
                ticketsConfig().createSection("Tickets");
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage( " §cOcorreu ao criar um arquivo tickets.yml em me.flugel.files.tickets");
            }
        } else {

            Bukkit.getConsoleSender().sendMessage(" §eArquivo [tickets.yml] carregado com sucesso!");
        }
    }

    public FileConfiguration ticketsConfig() {
        return fileConfiguration;
    }

    public void saveConfig() {

        try {
            fileConfiguration.save(tickets);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(" §cOcorreu um erro ao salvar o arquivo [tickets.yml]");
        }
    }
    public void loadConfiguration() {
        saveConfig();

    }

}




