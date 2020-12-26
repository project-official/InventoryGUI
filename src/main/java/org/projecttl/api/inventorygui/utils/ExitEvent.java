package org.projecttl.api.inventorygui.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class ExitEvent implements Listener {

    @EventHandler
    public void onClickedExitButton(InventoryClickEvent event, String getInventoryName) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(getInventoryName)) {
            if (Objects.requireNonNull(event.getCurrentItem()).getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_RED + "Exit")) {
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
}