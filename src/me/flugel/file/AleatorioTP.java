
package me.flugel.file;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import me.flugel.main.Main;
public class AleatorioTP {
    Main config = Main.getInstance();
    private File aleatorioTP;
    private FileConfiguration fileConfiguration;

    public AleatorioTP() {

        aleatorioTP = new File(config.getDataFolder(), "aleatorioTP.yml");
        fileConfiguration = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(aleatorioTP);
        if (!(aleatorioTP.exists())) {
            try {
                aleatorioTP.createNewFile();
                Bukkit.getConsoleSender().sendMessage(" §aLocal de armazenamento [aleatorioTP.yml] foi criado com sucesso!");
                aleatorioTPConfig().createSection("Cordenadas");
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage( " §cOcorreu ao criar um arquivo aleatorioTP.yml em me.flugel.files.aleatorioTP");
            }
        } else {

            Bukkit.getConsoleSender().sendMessage(" §eArquivo [aleatorioTP.yml] carregado com sucesso!");
        }
    }

    public FileConfiguration aleatorioTPConfig() {
        return fileConfiguration;
    }

    public void saveConfig() {

        try {
            fileConfiguration.save(aleatorioTP);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(" §cOcorreu um erro ao salvar o arquivo [aleatorioTP.yml]");
        }
    }
    public void loadConfiguration() {
        saveConfig();

    }

}



