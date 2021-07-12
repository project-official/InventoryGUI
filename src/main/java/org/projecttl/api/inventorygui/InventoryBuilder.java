package org.projecttl.api.inventorygui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.projecttl.api.inventorygui.listener.InventoryClickListener;
import org.projecttl.api.inventorygui.listener.InventoryCloseListener;
import org.projecttl.api.inventorygui.utils.InventoryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryBuilder {
    static List<InventoryBuilder> builders = new ArrayList<>();

    private final InventoryType type;
    private final Map<Integer, List<InventoryClickListener>> clickListenerMap = new HashMap<>();
    private final List<InventoryCloseListener> closeListeners = new ArrayList<>();
    private final Map<Integer, ItemStack> itemMap = new HashMap<>();
    private Component title;
    private Inventory inventory;

    public InventoryBuilder(Component title, InventoryType type) {
        this.title = title;
        this.type = type;
    }

    public Component getTitle() {
        return title;
    }

    public void setTitle(Component title) {
        this.title = title;
    }

    public void setItem(int slot, ItemStack stack) {
        itemMap.put(slot, stack);
    }

    public int registerListener(int slotNumber, InventoryClickListener listener) {
        clickListenerMap.putIfAbsent(slotNumber, new ArrayList<>());
        List<InventoryClickListener> listeners = clickListenerMap.get(slotNumber);
        listeners.add(listener);
        clickListenerMap.put(slotNumber, listeners);
        return listener.hashCode();
    }

    public void registerCloseListener(InventoryCloseListener listener) {
        closeListeners.add(listener);
    }

    public void removeListener(int slotNumber, int listenerId) {
        clickListenerMap.putIfAbsent(slotNumber, new ArrayList<>());
        List<InventoryClickListener> listeners = clickListenerMap.get(slotNumber);
        listeners.removeIf(listener -> listener.hashCode() == listenerId);
    }

    public Inventory build() {
        Inventory inventory;
        switch (type) {
            case CHEST_9:
                inventory = Bukkit.createInventory(null, 9);
                break;
            case CHEST_18:
                inventory = Bukkit.createInventory(null, 18);
                break;
            case CHEST_27:
                inventory = Bukkit.createInventory(null, 27);
                break;
            case CHEST_36:
                inventory = Bukkit.createInventory(null, 36);
                break;
            case CHEST_45:
                inventory = Bukkit.createInventory(null, 45);
                break;
            case CHEST_54:
                inventory = Bukkit.createInventory(null, 54);
                break;
            case HOPPER:
                inventory = Bukkit.createInventory(null, org.bukkit.event.inventory.InventoryType.HOPPER);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        for (int i = 0; i < inventory.getSize(); i++) {
            clickListenerMap.putIfAbsent(i, new ArrayList<>());
        }
        itemMap.forEach(inventory::setItem);
        builders.add(this);
        this.inventory = inventory;
        return inventory;
    }

    Inventory getInventory() {
        return inventory;
    }

    void executeClickListener(int slot, InventoryClickEvent event) {
        clickListenerMap.get(slot).forEach(listener -> listener.click(event));
    }

    void executeCloseListener(InventoryCloseEvent event) {
        closeListeners.forEach(listener -> listener.close(event));
    }
}
