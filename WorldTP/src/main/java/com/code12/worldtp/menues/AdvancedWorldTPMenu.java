package com.code12.worldtp.menues;

import com.code12.worldtp.WorldTP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class AdvancedWorldTPMenu {
    WorldTP plugin;
    public Inventory tpMenu;

    public AdvancedWorldTPMenu(WorldTP worldTP) {
        plugin = worldTP;

        int numberOfWorlds = plugin.getConfig().getStringList("menuGroupList").size();
        int numberOfSlots = 9;

        int j = 9;
        while((j/numberOfWorlds) < 1){
            j += 9;
            numberOfSlots = j;
        }

        tpMenu = Bukkit.createInventory(null, numberOfSlots, "World Menu");

        List<String> menuGroupList = plugin.getConfig().getStringList("menuGroupList");

        int i=0;
        for (String menuGroup : menuGroupList) {
            ItemStack item = new ItemStack(Material.GRASS_BLOCK);
            if(plugin.getConfig().getItemStack("menuGroupID." + menuGroup + ".item") != null){
                item = plugin.getConfig().getItemStack("menuGroupID." + menuGroup + ".item");
                assert item != null;
            }
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(plugin.getConfig().getString("menuGroupID." + menuGroup + ".displayName"));
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(itemMeta);
            tpMenu.setItem(i, item);
            i++;
        }
    }
}
