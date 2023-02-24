package com.bahzinga.btitles.frontend;

import com.bahzinga.btitles.Main;
import com.bahzinga.btitles.backend.api.BTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class BTitleClaim implements Listener {

    @EventHandler
    public void onClaim(PlayerInteractEvent event){

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Overcomplicated permissions fuckery
        HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
        PermissionAttachment attachment = player.addAttachment(Main.getPlugin(Main.class));
        perms.put(player.getUniqueId(), attachment);
        PermissionAttachment pperms = perms.get(player.getUniqueId());



        // Check if the item is a name tag, we don't want this code running every damn time an item is interacted with
        if (item.getType() == Material.NAME_TAG){

            // Run it in a new thread, because opTimIZatIon
            new BukkitRunnable() {

                public void run() {

                    // Check to see if the name tag has a stored id
                    if (BTitle.getStoredId(item) != null){

                        // Instantiate a new BTitle for convenience
                        BTitle title = new BTitle(BTitle.getStoredId(item));

                        // Check to see if the player has already unlocked it, if not
                        if (!player.hasPermission(title.getPermission())){

                            // Grant the permission, so this player will now able to claim the title and won't be able to reclaim it :D
                            pperms.setPermission(title.getPermission(), true);

                            // Remove the item from the inventory, they don't need it anymore, do they?
                            player.getInventory().remove(item);

                            // Notify the player
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&eTitles&8] &7You redeemed a new title: " + title.getTitleContent()));

                        } else { // If player already owns it call them a rude name and tell them to check their titles, smh
                            player.hasPermission(ChatColor.translateAlternateColorCodes('&', "&7Hey, it looks like you already own this &n/title&7! "));

                        }

                    }

                }

            }.runTask(Main.getPlugin(Main.class));

        }

    }

}
