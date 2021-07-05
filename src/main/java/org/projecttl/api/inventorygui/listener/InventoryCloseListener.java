package org.projecttl.api.inventorygui.listener;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

@Deprecated
public interface InventoryCloseListener {
    void close(InventoryCloseEvent event);
}
