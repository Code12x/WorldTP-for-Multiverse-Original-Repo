package com.code12.worldtp.menues;

import com.code12.worldtp.WorldTP;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WorldTPMenu{
    WorldTP plugin;
    public Inventory tpMenu;

    public WorldTPMenu(WorldTP worldTP){
        plugin = worldTP;

        List<String> worldList = plugin.getConfig().getStringList("menuGroupList");
        ArrayList<String> notAdminWorlds = new ArrayList<String>();

        for(String world : worldList){
            if(!plugin.getConfig().getBoolean("menuGroupID." + world + ".admin")){
                notAdminWorlds.add(world);
            }
        }

        int numberOfSlots = 9;

        int j = 9;
        while((j/notAdminWorlds.size()) < 1){
            j += 9;
            numberOfSlots = j;
        }

        tpMenu = Bukkit.createInventory(null, numberOfSlots, "World Menu");

        for (String menuGroup : notAdminWorlds) {
            ItemStack item = plugin.getConfig().getItemStack("menuGroupID." + menuGroup + ".item");
            assert item != null;
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(plugin.getConfig().getString("menuGroupID." + menuGroup + ".displayName"));
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(itemMeta);
            tpMenu.addItem(item);
        }
    }
}
