package com.code12.worldtp.listeners;

import com.code12.worldtp.WorldTP;
import com.code12.worldtp.apimethods.WorldTPWorld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class InventoryListener implements Listener {
    WorldTP plugin;
    public InventoryListener(WorldTP worldTP){
        plugin = worldTP;
    }

    @EventHandler
    public void onPlayerInventoryInteractEvent(InventoryClickEvent event) {
        List<String> menuGroupList = plugin.getConfig().getStringList("menuGroupList");

        Player player = (Player) event.getWhoClicked();

        if(event.getView().getTitle().contains("World Menu")) {
            if(event.getCurrentItem() == null){
                return;
            }
            if(event.getCurrentItem().getItemMeta() != null) {
                for(String menuGroup : menuGroupList){
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("menuGroupID." + menuGroup + ".displayName"))){

                        String worldToLeaveName = player.getWorld().getName();
                        WorldTPWorld worldToLeave = new WorldTPWorld(plugin, worldToLeaveName);
                        String worldGroupToLeave = worldToLeave.getWorldGroup();

                        Location playerLocation = player.getLocation();

                        Location locationToTP = null;

                        if(plugin.getConfig().getLocation("playerLocations."+player.getName()+"."+menuGroup) != null){
                            locationToTP = plugin.getConfig().getLocation("playerLocations."+player.getName()+"."+menuGroup);

                            String worldToEnterName = locationToTP.getWorld().getName();
                            WorldTPWorld worldToEnter = new WorldTPWorld(plugin, worldToEnterName);
                            String worldToEnterWorldGroup = worldToEnter.getWorldGroup();

                            if(worldGroupToLeave.equals(worldToEnterWorldGroup)){
                                player.sendMessage("You are already in the world: " + worldGroupToLeave);
                                event.setCancelled(true);
                                break;
                            }
                        }

                        if(plugin.getConfig().getLocation("menuGroupID." + menuGroup + ".WorldTPWorldSpawnPoint") != null){
                            locationToTP = plugin.getConfig().getLocation("menuGroupID." + menuGroup + ".WorldTPWorldSpawnPoint");
                        }

                        plugin.getConfig().set("playerLocations."+player.getName()+"."+worldGroupToLeave, playerLocation);

                        if(locationToTP != null){
                            player.teleport(locationToTP);
                        }else {
                            World world = Bukkit.getWorld(menuGroup);
                            player.teleport(world.getSpawnLocation());
                            if(player.getBedSpawnLocation() != null){
                                player.teleport(player.getBedSpawnLocation());
                            }
                        }
                        player.sendMessage(ChatColor.DARK_AQUA + "You have been teleported to " + menuGroup + ".");
                        plugin.saveConfig();
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
