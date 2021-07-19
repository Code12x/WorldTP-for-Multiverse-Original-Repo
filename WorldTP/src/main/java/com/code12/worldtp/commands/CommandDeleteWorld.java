package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import com.code12.worldtp.apimethods.WorldTPWorldGroup;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CommandDeleteWorld implements CommandExecutor {
    WorldTP plugin;

    public CommandDeleteWorld(WorldTP worldTP) {
        plugin=worldTP;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender.hasPermission("deleteworld"))){
            sender.sendMessage(ChatColor.YELLOW + "You do not have the necessary permission to perform this command.");
            return true;
        }

        if(!(args.length == 1)){
            sender.sendMessage(ChatColor.YELLOW + "Command \"deleteworld\" requires 1 argument: world to delete.\n" + ChatColor.AQUA + "Currently registered worlds:\n"+ChatColor.WHITE + plugin.getConfig().getStringList("worldList"));
            return true;
        }

        List<String> worlds = plugin.getConfig().getStringList("worldList");
        String world = args[0];
        String displayName = plugin.getConfig().getString("menuGroupID." + world + ".displayName");

        WorldTPWorldGroup worldTPWorldGroup = new WorldTPWorldGroup(plugin, world, displayName);

        worldTPWorldGroup.deleteWorldGroup(sender);

        return true;
    }
}
