package com.code12.worldtp;

import com.code12.worldtp.commands.*;
import com.code12.worldtp.listeners.InventoryListener;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

//Experimental for a bug
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

public final class WorldTP extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("WorldTP has been ENABLED");
        FileConfigurationOptions WorldTPConfig = getConfig().options().copyDefaults(true);
        saveConfig();

        // Plugin manager
        PluginManager pm = getServer().getPluginManager();
        // Registering an inventory listener for when a player clicks on the world he wants to tp to from the menu.
        pm.registerEvents(new InventoryListener(this), this);

        // getting all the player commands
        getCommand("worldtp").setExecutor(new CommandWorldTP(this)); // anyone can access
        getCommand("editworld").setExecutor(new CommandEditWorld(this)); // only admins
        getCommand("deleteworld").setExecutor(new CommandDeleteWorld(this)); // only admins
        getCommand("listworlds").setExecutor(new CommandListWorlds(this)); // anyone
        getCommand("reloadworlds").setExecutor(new CommandReloadWorlds(this));// only admins

        saveConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("WorldTP has been DISABLED");
        saveConfig();
    }
}
