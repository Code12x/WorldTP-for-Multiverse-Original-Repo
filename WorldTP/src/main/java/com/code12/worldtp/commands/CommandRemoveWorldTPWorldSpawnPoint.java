package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveWorldTPWorldSpawnPoint implements CommandExecutor {
    WorldTP plugin;
    public CommandRemoveWorldTPWorldSpawnPoint(WorldTP insance) {
        plugin = insance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!sender.hasPermission("worldtp.removeworldtpworldspawnpoint")){
            sender.sendMessage(ChatColor.YELLOW + "You don't have the necessary permission to use this command.");
        }

        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command.");
        }

        Player player = (Player) sender;

        String world = player.getWorld().getName();

        if(args.length != 0){
            player.sendMessage("This command requires 0 argumens. Go to the world where you want to remove the WorldTP World Spawn Point, and run the command to remove the WorldTP World Spawn Point.");
        }

        plugin.getConfig().set("menuGroupID." + world + ".WorldTPWorldSpawnPoint", null);

        player.sendMessage(ChatColor.YELLOW + "WorldTP World Spawn Point removed from world: " + world + "!");

        plugin.saveConfig();

        return true;
    }

}
