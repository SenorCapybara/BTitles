package com.bahzinga.btitles.backend.api;

import com.bahzinga.btitles.Main;
import com.bahzinga.btitles.backend.files.BTitleFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class BTitle {

    private static NamespacedKey stored_id = new NamespacedKey(Main.getPlugin(Main.class), "id");
    private String id;
    private String displayName;
    private List<String> lore;
    private int customModelData;
    private String titleContent;
    private String titleTooltip;
    private String titleAction;
    private String titleOutput;
    private String permission;

    public BTitle (String id){

        this.id = id;
        this.displayName = BTitleFile.getConfig().getString("titles." + id + ".displayname");
        this.lore = BTitleFile.getConfig().getStringList("titles." + id + ".lore");
        this.customModelData = BTitleFile.getConfig().getInt("titles." + id + ".custommodeldata");
        this.titleContent = BTitleFile.getConfig().getString("titles." + id + ".content");
        this.permission = BTitleFile.getConfig().getString("titles." + id + ".permission");
        this.titleTooltip = BTitleFile.getConfig().getString("titles." + id + ".titleTooltip");
        this.titleAction = BTitleFile.getConfig().getString("titles." + id + ".titleAction");
        this.titleOutput = BTitleFile.getConfig().getString("titles." + id + ".titleOutput");

    }

    public ItemStack claimItem(){


        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Title &8(" + this.displayName + "&8)"));
        List<String> Lore = new ArrayList<>();
        for (int i = 0;i<this.lore.size();i++){
            Lore.add(ChatColor.translateAlternateColorCodes('&', this.lore.get(i)));
        }
        Lore.add("");
        Lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oRight click while holding this item to claim your Title!"));
        meta.setLore(Lore);
        meta.setCustomModelData(this.customModelData);

        // Set the stored ID
        meta.getPersistentDataContainer().set(stored_id, PersistentDataType.STRING, this.id);

        item.setItemMeta(meta);

        return item;

    }



    public ItemStack getIcon(){
        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.displayName));
        List<String> Lore = new ArrayList<>();
        for (int i = 0;i<this.lore.size();i++){
            Lore.add(ChatColor.translateAlternateColorCodes('&', this.lore.get(i)));
        }
        meta.setLore(Lore);
        meta.setCustomModelData(this.customModelData);
        item.setItemMeta(meta);
        return item;
    }

    public static String getStoredId(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().get(stored_id, PersistentDataType.STRING);
    }

    public String getPermission(){
        return this.permission;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public List<String> getLore(){
        return this.lore;
    }

    public int getCustomModelData(){
        return this.customModelData;
    }

    public String getId(){
        return this.id;
    }

    public String getTitleContent(){
        return this.titleContent;
    }

    public String getTitleTooltip(){
        return this.titleTooltip;
    }

    public String getTitleAction(){
        return this.titleAction;
    }

    public String getTitleOutput(){
        return this.titleOutput;
    }



}
