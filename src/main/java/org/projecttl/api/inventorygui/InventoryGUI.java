package org.projecttl.api.inventorygui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class InventoryGUI extends JavaPlugin {
    private static InventoryGUI INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("<InventoryGUI> " + ChatColor.GREEN + "API has successful enabled.");

        Bukkit.getPluginManager().registerEvents(new Listener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("<InventoryGUI> " + ChatColor.DARK_RED + "API has successful disabled.");
    }

    public static InventoryGUI getInstance() {
        return INSTANCE;
    }
}