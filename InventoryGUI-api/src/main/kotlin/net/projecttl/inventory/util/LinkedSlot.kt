package net.projecttl.inventory.util

import org.bukkit.inventory.ItemStack

/**
 * A slot implementation, for LinkedInventoryBuilders. Unlike Slot, event-handling is separated to SlotHandler
 */
class LinkedSlot(stack: ItemStack, val handler: SlotHandler): Slot(stack, {})