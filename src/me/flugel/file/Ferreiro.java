
package me.flugel.file;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import me.flugel.main.Main;
public class Ferreiro {
    Main config = Main.getInstance();
    private File ferreiro;
    private FileConfiguration fileConfiguration;

    public Ferreiro() {

        ferreiro = new File(config.getDataFolder(), "ferreiro.yml");
        fileConfiguration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(ferreiro);
        if (!(ferreiro.exists())) {
            try {
                ferreiro.createNewFile();
                Bukkit.getConsoleSender().sendMessage(" §aLocal de armazenamento [ferreiro.yml] foi criado com sucesso!");
                ferreiroConfig().createSection("Cordenadas");
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage( " §cOcorreu ao criar um arquivo ferreiro.yml em me.flugel.files.ferreiro");
            }
        } else {

            Bukkit.getConsoleSender().sendMessage(" §eArquivo [ferreiro.yml] carregado com sucesso!");
        }
    }

    public FileConfiguration ferreiroConfig() {
        return fileConfiguration;
    }

    public void saveConfig() {

        try {
            fileConfiguration.save(ferreiro);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(" §cOcorreu um erro ao salvar o arquivo [ferreiro.yml]");
        }
    }
    public void loadConfiguration() {
        saveConfig();

    }

}


