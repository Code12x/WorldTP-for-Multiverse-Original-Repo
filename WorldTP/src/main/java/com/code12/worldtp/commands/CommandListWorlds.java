package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListWorlds implements CommandExecutor {

    WorldTP plugin;

    public CommandListWorlds(WorldTP worldTP) {
        plugin = worldTP;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!sender.hasPermission("worldtp.listworlds")){
            sender.sendMessage(ChatColor.YELLOW + "You don't have the necessary permission to use this command.");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Worlds:\n " + ChatColor.WHITE + plugin.getConfig().getStringList("menuGroupList"));

        return true;
    }
}
