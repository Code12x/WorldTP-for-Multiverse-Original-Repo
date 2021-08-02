package com.code12.worldtp.listeners;

import com.code12.worldtp.WorldTP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    WorldTP plugin;

    public PlayerJoinListener(WorldTP instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if(plugin.getConfig().getLocation("lobby") != null){
            event.getPlayer().teleport(plugin.getConfig().getLocation("lobby"));
        }
    }
}
