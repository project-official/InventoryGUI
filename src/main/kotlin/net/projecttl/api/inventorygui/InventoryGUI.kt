package net.projecttl.api.inventorygui

import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class InventoryGUI: JavaPlugin() {

    override fun onEnable() {
        logger.info("<InventoryGUI> ${ChatColor.GREEN}InventoryGUI API is successful loaded!")
    }

    override fun onDisable() {
        logger.info("<InventoryGUI> ${ChatColor.GREEN}InventoryGUI API is successful unloaded!")
    }
}