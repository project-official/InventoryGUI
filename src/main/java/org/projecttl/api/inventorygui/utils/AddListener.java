package org.projecttl.api.inventorygui.utils;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public abstract class AddListener implements Listener {
    @Deprecated
    public AddListener(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @Deprecated
    public abstract void onEvent(InventoryClickEvent event);
}