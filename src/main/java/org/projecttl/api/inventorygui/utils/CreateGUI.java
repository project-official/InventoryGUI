package org.projecttl.api.inventorygui.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

@Deprecated
public class CreateGUI {

    @Deprecated
    private final Plugin plugin;
    @Deprecated
    private TextComponent name;
    @Deprecated
    private Inventory inventory;
    @Deprecated
    private String legacyName;

    /* Start name area */

    @Deprecated
    public CreateGUI(int size, TextComponent name, Plugin plugin) {
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, name);
        this.plugin = plugin;
    }


    @Deprecated
    public CreateGUI(int size, String name, Plugin plugin) {
        this.legacyName = name;
        this.inventory = Bukkit.createInventory(null, size, name);
        this.plugin = plugin;
    }

    /* End name area */

    /* Start inventory area */

    @Deprecated
    public TextComponent setName(TextComponent inventoryName) {
        return name = inventoryName;
    }

    @Deprecated
    public TextComponent getName() {
        return name;
    }

    /* End inventory area */

    /* Start item Area */

    @Deprecated
    public Inventory setInventory(Inventory getInventory) {
        return inventory = getInventory;
    }

    @Deprecated
    public Inventory getInventory() {
        return inventory;
    }

    @Deprecated
    public void setItem(ItemStack item, int itemLocation) {
        inventory.setItem(itemLocation, item);
    }

    @Deprecated
    public void setItem(ItemStack item, TextComponent name, int itemLocation) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        item.setItemMeta(meta);

        inventory.setItem(itemLocation, item);
    }

    /* End item Area */

    /* Start event Area*/

    @Deprecated
    public void setItem(ItemStack item, TextComponent name, List<Component> lore, int itemLocation) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        item.setItemMeta(meta);

        inventory.setItem(itemLocation, item);
    }

    /* End event Area */

    /* Start Open Inventory */

    @Deprecated
    public void setItem(ItemStack item, TextComponent name, List<Component> lore, int itemLocation, boolean setEnchant) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        item.setItemMeta(meta);

        if (setEnchant) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }

        inventory.setItem(itemLocation, item);
    }

    /* End Open Inventory */

    @Deprecated
    public void addEvent(AddListener listener) {

    }

    /* Deprecated Code START */

    @Deprecated
    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    @Deprecated
    public void setExit(TextComponent inventoryName, int itemLocation) {
        setItem(new ItemStack(Material.BARRIER), Component.text(ChatColor.DARK_RED + "Exit"), itemLocation);
        addEvent(
                new AddListener(plugin) {
                    @Override
                    @EventHandler
                    public void onEvent(InventoryClickEvent event) {
                        Player player = (Player) event.getWhoClicked();

                        if (event.getView().title().equals(inventoryName)) {
                            if (event.getCurrentItem().getItemMeta().displayName().equals(Component.text(ChatColor.DARK_RED + "Exit"))) {
                                player.closeInventory();
                                event.setCancelled(true);
                            }
                        }
                    }
                });
    }

    @Deprecated
    public String setName(String getInventoryName) {
        return legacyName = getInventoryName;
    }

    @Deprecated
    public String getLegacyName() {
        return legacyName;
    }

    @Deprecated
    public void setItem(ItemStack item, String name, int itemLocation) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        inventory.setItem(itemLocation, item);
    }

    @Deprecated
    public void setItem(ItemStack item, String name, List<String> lore, int itemLocation) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(itemLocation, item);
    }

    @Deprecated
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

    @Deprecated
    public void setExitButton(int itemLocation) {
        setItem(new ItemStack(Material.BARRIER), ChatColor.DARK_RED + "Exit", itemLocation);
    }

    /* Deprecated Code END */
}
