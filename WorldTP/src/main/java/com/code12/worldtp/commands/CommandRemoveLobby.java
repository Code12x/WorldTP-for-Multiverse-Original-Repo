package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveLobby implements CommandExecutor {
    WorldTP plugin;
    public CommandRemoveLobby(WorldTP instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!sender.hasPermission("worldtp.removelobby")){
            sender.sendMessage(ChatColor.YELLOW + "You don't have the necessary permission to use this command.");
        }

        plugin.getConfig().set("lobby", null);

        sender.sendMessage(ChatColor.YELLOW + "There are no longer any lobbies registered.");

        plugin.saveConfig();

        return true;
    }

}
