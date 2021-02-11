package org.projecttl.api.inventorygui.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CreateGUI implements Listener {

    private String name;
    private Inventory inventory;

    public CreateGUI(int size, String name) {
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    /* Start name area */

    public String setName(String getInventoryName) {
        return name = getInventoryName;
    }

    public String getName() {
        return name;
    }

    /* End name area */

    /* Start inventory area */

    public Inventory setInventory(Inventory getInventory) {
        return inventory = getInventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    /* End inventory area */

    /* Start item Area */

    public void setItem(ItemStack item, int itemLocation) {
        inventory.setItem(itemLocation, item);
    }

    public void setItem(ItemStack item, String name, int itemLocation) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        inventory.setItem(itemLocation, item);
    }

    public void setItem(ItemStack item, String name, List<String> lore, int itemLocation) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(itemLocation, item);
    }

    public void setItem(ItemStack item, String name, List<String> lore, int itemLocation, boolean setEnchant) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        if (setEnchant) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }

        inventory.setItem(itemLocation, item);
    }

    /* End item Area */

    /* Start Open Inventory */

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    /* End Open Inventory */

    public void setExitButton(int itemLocation) {
        setItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Exit", itemLocation);
    }
}
