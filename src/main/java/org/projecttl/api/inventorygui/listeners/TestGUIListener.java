package org.projecttl.api.inventorygui.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.projecttl.api.inventorygui.commands.ConfigInventoryAPI;

import java.util.Objects;

public class TestGUIListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(ConfigInventoryAPI.inventoryName)) {
            if (Objects.requireNonNull(event.getCurrentItem()).getItemMeta().getDisplayName().equalsIgnoreCase(ConfigInventoryAPI.item_1)) {
                event.setCancelled(true);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ConfigInventoryAPI.item_2)) {
                event.setCancelled(true);
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Exit")) {
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
}
