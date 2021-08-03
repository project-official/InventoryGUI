package net.projecttl.inventory.gui

import net.projecttl.inventory.gui.utils.InventoryType
import net.projecttl.inventory.gui.utils.Slot
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

fun Plugin.gui(slotType: InventoryType, title: String, init: InventoryGuiBuilder.() -> Unit) : Inventory {
    return InventoryGuiBuilder(slotType, title, this).apply(init).build()
}

fun gui(slotType: InventoryType, title: String, plugin: Plugin, init: InventoryGuiBuilder.() -> Unit) : Inventory {
    return InventoryGuiBuilder(slotType, title, plugin).apply(init).build()
}

class InventoryGuiBuilder(val slotType: InventoryType, val title: String, val plugin: Plugin) : Listener {

    private val slots = hashMapOf<Int, Slot>()

    fun slot(slot: Int, item: ItemStack, handler: InventoryClickEvent.() -> Unit) {
        slots[slot] = Slot(item, handler)
    }

    fun slot(slot: Int, item: ItemStack) {
        slot(slot, item) {}
    }

    fun build() : Inventory {
        val inv = Bukkit.createInventory(null, slotType.name.split("_")[1].toInt(), title)
        for (slot in slots.entries) {
            inv.setItem(slot.key, slot.value.stack)
        }
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
        return inv
    }

    @EventHandler
    private fun listener(event: InventoryClickEvent) {
        if(event.currentItem != null) {
            for(slot in slots.entries) {
                if(event.view.title == this.title && slot.key == event.slot) {
                    event.isCancelled = true
                    slot.value.click(event)
                }
            }
        }
    }

}