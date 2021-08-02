package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetLobby implements CommandExecutor {
    WorldTP plugin;

    public CommandSetLobby(WorldTP instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command.");
        }

        if(!(sender.hasPermission("worldtp.setlobby"))) {
            sender.sendMessage(ChatColor.YELLOW + "You don't have the necessary permission to use this command.");
            return true;
        }

        if(args.length != 0){
            sender.sendMessage("This command requires 0 arguments. Use it by running \"/setlobby\" which will set the player join position in the world that the player is in at the position where you run the command.");
        }

        Player player = (Player) sender;

        Location loc = player.getLocation();

        plugin.getConfig().set("lobby", loc);

        player.sendMessage(ChatColor.YELLOW + "Lobby position set!");

        plugin.saveConfig();

        return true;
    }
}