package net.projecttl.inventory.gui.utils

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

data class Slot(val stack: ItemStack, val ignoreClick: Boolean, val click: InventoryClickEvent.() -> Unit)