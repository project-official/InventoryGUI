package org.projecttl.api.inventorygui.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.projecttl.api.inventorygui.InventoryGUI;
import org.projecttl.api.inventorygui.utils.AddListener;
import org.projecttl.api.inventorygui.utils.CreateGUI;

import java.util.ArrayList;
import java.util.List;

public class ConfigInventoryAPI implements CommandExecutor, TabCompleter {

    private final InventoryGUI api;
    public ConfigInventoryAPI(InventoryGUI api) {
        this.api = api;
    }

    public static String inventoryName = ChatColor.GREEN + "Test GUI";
    public static String item_1 = ChatColor.GOLD + "Test_1";
    public static String item_2 = ChatColor.BLUE + "Test_2";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gui")) {
            if (args.length == 0) {
                return false;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("example1")) {
                    CreateGUI testGUI = new CreateGUI(27, Component.text(inventoryName), api);
                    testGUI.setItem(new ItemStack(Material.BEDROCK), Component.text(item_1), 10);
                    testGUI.setItem(new ItemStack(Material.COMPASS), Component.text(item_2), 13);

                    testGUI.addEvent(new AddListener(api) {
                        @Override
                        @EventHandler
                        public void onEvent(InventoryClickEvent event) {
                            if (event.getView().title().equals(Component.text(inventoryName))) {
                                Component component = event.getCurrentItem().getItemMeta().displayName();

                                if (component.equals(Component.text(item_1))) {
                                    event.setCancelled(true);
                                } else if (component.equals(Component.text(item_2))) {
                                    event.setCancelled(true);
                                }
                            }
                        }
                    });

                    testGUI.setExit(Component.text(inventoryName), 16);

                    testGUI.openInventory(((Player) sender));
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> firstArgument = new ArrayList<>();
            firstArgument.add("example1");

            return firstArgument;
        }

        return null;
    }
}
