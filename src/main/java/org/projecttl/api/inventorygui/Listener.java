package org.projecttl.api.inventorygui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        InventoryBuilder.builders.forEach(builder -> {
            if (event.getInventory().equals(builder.getInventory())) {
                builder.executeListener(event.getSlot(), event);
            }
        });
    }
}
