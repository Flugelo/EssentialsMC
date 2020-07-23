
package me.flugel.file;

import me.flugel.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Banidos {
    Main config = Main.getInstance();
    private File banidos;
    private FileConfiguration fileConfiguration;

    public Banidos() {

        banidos = new File(config.getDataFolder(), "banidos.yml");
        fileConfiguration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(banidos);
        if (!(banidos.exists())) {
            try {
                banidos.createNewFile();
                Bukkit.getConsoleSender().sendMessage(" §aLocal de armazenamento [banidos.yml] foi criado com sucesso!");
                banidosConfig().createSection("banidos");
                saveConfig();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage( " §cOcorreu ao criar um arquivo banidos.yml em me.flugel.files.banidos");
            }
        } else {

            Bukkit.getConsoleSender().sendMessage(" §eArquivo [banidos.yml] carregado com sucesso!");
        }
    }

    public FileConfiguration banidosConfig() {
        return fileConfiguration;
    }

    public void saveConfig() {

        try {
            fileConfiguration.save(banidos);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(" §cOcorreu um erro ao salvar o arquivo [banidos.yml]");
        }
    }
    public void loadConfiguration() {
        saveConfig();

    }

}




