package org.projecttl.api.inventorygui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        InventoryBuilder.builders.forEach(builder -> {
            if (event.getInventory().equals(builder.getInventory())) {
                builder.executeClickListener(event.getSlot(), event);
            }
        });
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent event) {
        InventoryBuilder.builders.forEach(builder -> {
            if (event.getInventory().equals(builder.getInventory())) {
                builder.executeCloseListener(event);
            }
        });
    }
}
