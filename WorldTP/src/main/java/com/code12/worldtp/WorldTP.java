package com.code12.worldtp;

import com.code12.worldtp.commands.*;
import com.code12.worldtp.listeners.InventoryListener;
import com.code12.worldtp.listeners.PlayerJoinListener;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        pm.registerEvents(new PlayerJoinListener(this), this);

        // getting all the player commands
        getCommand("worldtp").setExecutor(new CommandWorldTP(this)); // anyone can access
        getCommand("editworld").setExecutor(new CommandEditWorld(this)); // only admins
        getCommand("deleteworld").setExecutor(new CommandDeleteWorld(this)); // only admins
        getCommand("listworlds").setExecutor(new CommandListWorlds(this)); // anyone
        getCommand("reloadworlds").setExecutor(new CommandReloadWorlds(this));// only admins
        getCommand("setlobby").setExecutor(new CommandSetLobby(this)); // only admins
        getCommand("setworldtpworldspawnpoint").setExecutor(new CommandSetWorldTPWorldSpawnPoint(this)); //admins only
        getCommand("removelobby").setExecutor(new CommandRemoveLobby(this)); //admins only
        getCommand("removeworldtpworldspawnpoint").setExecutor(new CommandRemoveWorldTPWorldSpawnPoint(this));

        saveConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("WorldTP has been DISABLED");
        saveConfig();
    }
}
