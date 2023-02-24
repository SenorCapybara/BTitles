package com.bahzinga.btitles.backend.files;


import com.bahzinga.btitles.Main;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BTitleFile {

    private static File file = new File(Main.getPlugin(Main.class).getDataFolder(), "/config.yml");
    private static FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

    public static void save() {
        YamlConfiguration.loadConfiguration(file);
    }

    public static File getFile() {
        return file;
    }

    public static FileConfiguration getFileConf() {
        return yml;
    }

    public static void create() {


        // If file doesn't exist
        if (!file.exists()) {

            File dir = file.getParentFile();
            dir.mkdirs();
            try {
                file.createNewFile();
                yml.set("titles.example.displayname", "&eExample Title");
                yml.set("titles.example.lore", Arrays.asList("This is a test title!"));
                yml.set("titles.example.content", "&8[&eExample&8]");
                yml.set("titles.example.custommodeldata", 1);
                yml.set("titles.example.permission", "btitles.example");
                yml.set("titles.example.titleTooltip", "&8// &7This player is using the &eExample &7title!");
                yml.set("titles.example.titleAction", "RUN_COMMAND");
                yml.set("titles.example.titleOutput", "/messsage <PLAYER>");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        try {
            yml.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static FileConfiguration getConfig() {
        return yml;
    }
}