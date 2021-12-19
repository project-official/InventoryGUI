package net.projecttl.inventory.gui

import net.kyori.adventure.text.Component
import net.projecttl.inventory.util.InventoryType
import net.projecttl.inventory.util.Slot
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.collections.HashMap

/**
 * The abstract implementation of the inventory builder
 */
interface InventoryBuilder {

    /**
     * The owner of this inventory
     */
    val player: Player

    /**
     * The slotType
     */
    val slotType: InventoryType

    /**
     * The title of this inventory
     */
    val title: Component

    /**
     * The inventory id
     */
    val id: UUID

    /**
     * The built inventory
     */
    val inventory: Inventory

    /**
     * The slots
     */
    val slots: HashMap<Int, out Slot>

    /**
     * Builds the current builder into the Bukkit API's Inventory
     */
    fun build(): Inventory

    /**
     * Registers a slot with a click listener
     */
    fun slot(slot: Int, item: ItemStack, handler: InventoryClickEvent.() -> Unit)

    /**
     * Registers a slot
     */
    fun slot(slot: Int, item: ItemStack)
    
    /**
     * On close inventory
     */
    fun onClose(handler: InventoryCloseEvent.() -> Unit)

    /**
     * Close inventory for the current player
     */
    fun close()

    /**
     * Destroys the current builder and the built inventory. The inventory will be closed for the player too.
     */
    fun destroy()
}
