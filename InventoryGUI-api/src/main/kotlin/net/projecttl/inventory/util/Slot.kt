package net.projecttl.inventory.util

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Slot for the SimpleInventoryBuilder
 */
open class Slot(val stack: ItemStack, val click: InventoryClickEvent.() -> Unit)