package org.projecttl.api.inventorygui;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.projecttl.api.inventorygui.commands.ConfigInventoryAPI;
import org.projecttl.api.inventorygui.commands.arguments.ConfigInventoryAPIArgs;

import java.util.Objects;

public class InventoryGUI extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("<Inventory_GUI> " + ChatColor.GREEN + "API has successful enabled.");

        Objects.requireNonNull(getCommand("gui")).setExecutor(new ConfigInventoryAPI());
        Objects.requireNonNull(getCommand("gui")).setTabCompleter(new ConfigInventoryAPIArgs());
    }

    @Override
    public void onDisable() {
        getLogger().info("<Inventory> " + ChatColor.DARK_RED + "API has successful disabled.");
    }
}