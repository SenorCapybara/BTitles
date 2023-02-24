package com.bahzinga.btitles;

import com.bahzinga.btitles.backend.files.BTitleFile;
import com.bahzinga.btitles.frontend.BTitleClaim;
import com.bahzinga.btitles.frontend.BTitleCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable(){

        BTitleFile.create();
        getServer().getPluginManager().registerEvents(new BTitleClaim(), this);
        getCommand("btitles").setExecutor(new BTitleCommand());

    }

    public void onDisable(){

    }

}