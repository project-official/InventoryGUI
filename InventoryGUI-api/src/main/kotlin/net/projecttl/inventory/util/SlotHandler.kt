package net.projecttl.inventory.util

import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Slot Handler for the LinkedSlot.
 */
class SlotHandler {
    @Suppress("WeakerAccess")
    val click = ArrayList<(InventoryClickEvent) -> Unit>()

    fun onClick(action: (InventoryClickEvent) -> Unit) {
        click.add(action)
    }
}