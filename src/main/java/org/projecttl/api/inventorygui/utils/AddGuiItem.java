package org.projecttl.api.inventorygui.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class AddGuiItem {

    public void onCreateItem(Inventory getInventory,
                             int setItemLocation,
                             Material setItemType,
                             String setItemName,
                             List<String> setItemLore,
                             int setItemAmount,
                             boolean setEnchanted) {

        ItemStack getItem = new ItemStack(setItemType);
        ItemMeta meta = getItem.getItemMeta();

        meta.setDisplayName(setItemName);
        meta.setLore(setItemLore);

        getItem.setItemMeta(meta);
        getItem.setAmount(setItemAmount);

        if (setEnchanted) {
            getItem.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
        }

        getInventory.setItem(setItemLocation, getItem);
    }

    public void onCreateExitButton(Inventory getInventory, int setItemLocation) {
        onCreateItem(getInventory,
                setItemLocation,
                Material.BARRIER,
                ChatColor.DARK_RED + "Exit",
                Arrays.asList(ChatColor.GOLD +  "If you click this button,", ChatColor.GOLD + "You can exit this GUI."),
                1,
                false);
    }
}