package com.code12.worldtp.commands;

import com.code12.worldtp.WorldTP;
import com.code12.worldtp.apimethods.WorldTPWorld;
import com.code12.worldtp.apimethods.WorldTPWorldGroup;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class CommandReloadWorlds implements CommandExecutor {

    WorldTP plugin;
    public CommandReloadWorlds(WorldTP instance){
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!sender.hasPermission("worldtp.reloadworlds")){
            sender.sendMessage(ChatColor.YELLOW + "You don't have the necessary permission to use this command.");
            return true;
        }

        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager mvWorldManager = core.getMVWorldManager();

        Collection<MultiverseWorld> multiverseWorldList = mvWorldManager.getMVWorlds();

        /* Iterate through each world and then fix worlds based on the world name. */
        //gets a list of all the overworlds.

        plugin.getConfig().set("menuGroupList", null);

        ArrayList<String> menuGroupList = new ArrayList<>();
        for(MultiverseWorld multiverseWorld : multiverseWorldList){
            String world = multiverseWorld.getName().toLowerCase();
            WorldTPWorld worldTPWorld = new WorldTPWorld(plugin, world);

            if(worldTPWorld.getWorldType().equals("overworld")){
                menuGroupList.add(world);
            }
        }

        //gets the menuGroupList and registers them with WorldTPWorldGroup.registerWorldGroup()
        for(String worldGroup : menuGroupList){
            ItemStack item = new ItemStack(Material.GRASS_BLOCK);
            String displayName = worldGroup;
            if(plugin.getConfig().getItemStack("menuGroupID." + worldGroup + ".item") != null){
                item = plugin.getConfig().getItemStack("menuGroupID." + worldGroup + ".item");
            }
            if(plugin.getConfig().getString("menuGroupID." + worldGroup + ".displayName") != null){
                displayName = plugin.getConfig().getString("menuGroupID." + worldGroup + ".displayName");
            }
            Boolean adminOnly = plugin.getConfig().getBoolean("menuGroupID." + worldGroup + ".admin");
            WorldTPWorldGroup worldTPWorldGroup = new WorldTPWorldGroup(plugin, worldGroup, displayName);
            worldTPWorldGroup.setItem(item);
            worldTPWorldGroup.setAdminOnly(adminOnly);
            worldTPWorldGroup.registerWorldGroup();

            for(MultiverseWorld multiverseWorld : multiverseWorldList){
                String multiverseWorldName = multiverseWorld.getName();
                WorldTPWorld world = new WorldTPWorld(plugin, multiverseWorldName);
                if(world.getName().startsWith(worldGroup)){
                    String worldType = world.getWorldType();
                    if(worldType.equals("overworld")){
                        plugin.getConfig().set("worldGroup." + worldGroup + ".overworld", world.getName());
                    }else if(worldType.equals("nether")){
                        plugin.getConfig().set("worldGroup." + worldGroup + ".nether", world.getName());
                    }else if(worldType.equals("the_end")){
                        plugin.getConfig().set("worldGroup." + worldGroup + ".the_end", world.getName());
                    }
                    plugin.saveConfig();
                }
            }
        }

        plugin.saveConfig();

        sender.sendMessage(ChatColor.YELLOW + "Reload of worlds Complete");

        return true;
    }
}
