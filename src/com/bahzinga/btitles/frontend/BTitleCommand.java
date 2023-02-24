package com.bahzinga.btitles.frontend;

import com.bahzinga.btitles.Main;
import com.bahzinga.btitles.backend.api.BTitle;
import com.bahzinga.btitles.backend.files.BTitleFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BTitleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (s.equalsIgnoreCase("btitles")){

            // Only players can send the command
            if (commandSender instanceof Player){

                // Get player
                Player player = ((Player) commandSender).getPlayer();

                // Check if player has permission
                if (player.hasPermission("btitles.admin")){

                    // Check arguments, if args.length == 0 send help page
                    if (args.length == 0){

                        List<String> help = Arrays.asList(
                                "&8------------ &3&lB&b&lTitles &8------------ ",
                                " &7Version: &e" + Main.getPlugin(Main.class).getDescription().getVersion(),
                                " &7Author: &eBahzinga",
                                "&8------------ &3&lB&b&lTitles &8------------ ",
                                " &8- &6/btitles &ereload&8: &7Reload the plugin",
                                " &8- &6/btitles &egive <player> <title>&8: &7Give the player a title they can claim",
                                "&8------------ &3&lB&b&lTitles &8------------ ",
                                ""
                        );

                    } else {    // If args.length != 0, allow player to send subcommands

                        // Reload subcommand
                        if (args[0].equalsIgnoreCase("reload")){
                            try {
                                BTitleFile.getFileConf().load(BTitleFile.getFile());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (InvalidConfigurationException e) {
                                throw new RuntimeException(e);
                            }

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    "&3&lB&b&lTitles &8// &7Successfully reloaded the plugin!"));
                        // Give subcommand
                        } else if (args[0].equalsIgnoreCase("give")){
                            // Check to see if the player is online
                            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))){

                                // Check if the title exists
                                if (BTitleFile.getConfig().getConfigurationSection("titles").getKeys(false).contains(args[2])){

                                    // TEST CODE
                                    BTitle title = new BTitle("example");
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', title.getDisplayName()));

                                    // Give the player the claim item
                                    player.getInventory().addItem(title.claimItem());


                                // Tell the player that title doesn't exist, and they're dumb :D
                                } else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lB&b&lTitles &8// &7There's no record of that title."));
                                }


                            } else { // Player isn't online
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lB&b&lTitles &8// &7Unable to find that player!"));
                            }
                        }

                    }


                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cError&8] &7You do not have permission to access that command."));
                }

            }

        }


        return false;
    }
}
