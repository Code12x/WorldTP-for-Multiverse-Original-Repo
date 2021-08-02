package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetWorldTPWorldSpawnPoint implements CommandExecutor {
    WorldTP plugin;

    public CommandSetWorldTPWorldSpawnPoint(WorldTP instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command.");
        }

        if(!(sender.hasPermission("worldtp.setworldtpworldspawnpoint"))) {
            sender.sendMessage(ChatColor.YELLOW + "You don't have the necessary permission to use this command.");
            return true;
        }

        if(args.length != 0){
            sender.sendMessage("This command requires 0 arguments. Use it by running \"/setworldtpworldspawnpoint\". This will make players teleport to the position in the world that you ran the command when they enter the world.");
        }

        Player player = (Player) sender;

        Location loc = player.getLocation();

        String world = player.getWorld().getName();

        plugin.getConfig().set("menuGroupID." + world + ".WorldTPWorldSpawnPoint", loc);

        player.sendMessage(ChatColor.YELLOW + "WorldTP World Spawn Point set!");

        plugin.saveConfig();

        return true;
    }

}