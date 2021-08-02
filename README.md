# WorldTP-for-Multiverse
A world menu for your worlds on your server

About:
WorldTP is a plugin that players can use to teleport to different worlds within a server. It uses a GUI (World Menu) to make the list of worlds to choose from easier for players to identify.

WorldTP uses the Multiverse-Core api AND the Multiveres-inventories api, so you MUST HAVE "Multiverse-Core" AND "Multiverse-Inventories" for this plugin to work. Multiverse-NetherPortals is also HIGHLY recommended to have installed on your server or some aspects of the plugin may not work on your server.

[!] NOTICE! This plugin is designed for SMPs in mind. In order for dimensions to properly link for this plugin, dimensions must follow the standard world naming format. (world, world_nether, world_the_end) Though, you CAN have a stand-alone overworld dimension that will show up on the menu. You CANNOT have a stand-alone nether or end dimension in the menu.

Features:
1. A "World Menu" menu (GUI).
2. Each world group in the World Menu can have customized display items and custom display names with the command /editworld.
3. Admin-only worlds! Use /editworld to make any world an admin-only world.
4. Automatically finds worlds and registers them when the command /reloadworlds is run.
5. Players will pick up right where they left off in any given world group. (So, no more teleporting to the end dimension without traveling there.)
6. Permissions!
7. Lobby world. You can set a world to be the world that all players teleport to when they join the server.
8. Instead of having players pick up where they left off in a world, you can set a WorldTP spawn point worlds which will teleport players to a set place in that world when they teleport with the worldtp menu. A WorldTP spawn point can be set for multiple worlds.

Commands:
  worldtp:
    descrition: Opens a menu for the player to navigate between worlds
    usage: /<command>
    permission: worldtp.worldtp (Permission "worldtp.worldtp" opens the worldtp menu with the admin-only worlds included)

  editworld:
    description: Allows an admin to edit the registery of a world.
    usage: /<command> [world name] [display name] [item to be displayed] [admin only (true/false)] (To name the item to be displayed, enter any one of these Material names from the spigot api docs: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html)
    permission: worldtp.editworld

  deleteworld:
    description: Allows an admin to delete a registered world group. (only a virtual delete, doesn't acturally delete the world file)
    usage: /<command> [world to delete]
    permission: worldtp.deleteworld

  listworlds:
    description: Outputs a list of the worlds on a server to the player who issued the command.
    usage: /<command>
    permission: worldtp.listworlds

  reloadworlds:
    description: Reregisters all the worlds for the WorldTP menu.
    usage: /<command>
    permission: worldtp.reloadworlds

setlobby:
  description: Sets a world to be the world that players spawn at when they join the server.
  usage: /<command>
  permission: worldtp.setlobby

setworldtpworldspawnpoint:
  description: Sets a location that players teleport to when they enter that world.
  usage: /<command>
  permission: worldtp.setworldtpworldspawnpoint

removelobby:
  description: Removes the lobby spawn point location from the server.
  usage: /<command>
  permission: worldtp.removelobby

removeworldtpworldspawnpoint:
  description: Removes the WorldTP World Spawn Point for the world that the player is in when the command is run.
  usage: /<command>
  permission: worldtp.removeworldtpworldspawnpoint
