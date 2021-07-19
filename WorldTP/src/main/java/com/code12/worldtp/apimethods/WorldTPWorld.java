package com.code12.worldtp.apimethods;

import com.code12.worldtp.WorldTP;

import java.util.List;

public class WorldTPWorld {

    //Variables
    WorldTP plugin;

    private String name;
    private String worldType;
    private String worldGroup;

    public WorldTPWorld(WorldTP instance, String worldName){
        plugin = instance;
        this.name = worldName;
    }

    public String getWorldGroup(){
        List<String> menuGroupList = plugin.getConfig().getStringList("menuGroupList");
        for(String menuGroup : menuGroupList){
            if(name.startsWith(menuGroup)){
                worldGroup = menuGroup;
            }
        }
        return worldGroup;
    }

    public String getWorldType(){
        if (name.toLowerCase().endsWith("nether")) {
            worldType = "nether";
        }else if(name.toLowerCase().endsWith("the_end")){
            worldType = "the_end";
        }else{
            worldType = "overworld";
        }

        return worldType;
    }

    public String getName() {
        return name;
    }
}
