package net.projecttl.inventory.gui

import net.kyori.adventure.text.Component
import net.projecttl.inventory.gui.utils.InventoryType
import net.projecttl.inventory.gui.utils.Slot
import org.bukkit.Bukkit
import org.bukkit.block.Container
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.util.*
import kotlin.collections.HashMap

val inventoryIds = HashMap<UUID, InventoryGuiBuilder>()

fun Player.gui(plugin: Plugin, slotType: InventoryType, title: Component, init: InventoryGuiBuilder.() -> Unit) {
    val a = InventoryGuiBuilder(player!!, slotType, title, plugin)
    a.apply(init).build()
}

/*
fun gui(player: Player, slotType: InventoryType, title: Component, plugin: Plugin, init: InventoryGuiBuilder.() -> Unit) : Inventory {
    val a = InventoryGuiBuilder(slotType, title, plugin)
    return a.apply(init).build()
}
 */

class InventoryGuiBuilder(val player: Player, val slotType: InventoryType, val title: Component, val plugin: Plugin) : Listener {

    val slots = hashMapOf<Int, Slot>()

    private val inventoryId = UUID.randomUUID()

    private lateinit var inv: Inventory

    init {
        inventoryIds[inventoryId] = this
    }

    fun slot(slot: Int, item: ItemStack, handler: InventoryClickEvent.() -> Unit) {
        slots[slot] = Slot(item, handler)
    }

    fun slot(slot: Int, item: ItemStack) {
        slot(slot, item) {}
    }

    fun close() {
        if(this::inv.isInitialized)
            inv.close()
    }

    fun build() : Inventory {
        inv = Bukkit.createInventory(null, slotType.name.split("_")[1].toInt(), title)
        for (slot in slots.entries) {
            inv.setItem(slot.key, slot.value.stack)
        }
        player.openInventory(inv)
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
        return inv
    }

    @EventHandler
    private fun listener(event: InventoryClickEvent) {
        if(event.view.title() == this.title) {
            if (inventoryIds.contains(inventoryId) && event.currentItem != null && event.view.player == player) {
                if (event.inventory == inv) {
                    for (slot in slots.entries) {
                        if (slot.key == event.slot) {
                            slot.value.click(event)
                        }
                    }

                    event.isCancelled = true
                }
            }
        }
    }

    @EventHandler
    private fun listener2(event: InventoryMoveItemEvent) {
        if (inventoryIds.contains(inventoryId) && event.source.holder?.inventory?.viewers?.contains(player)!! && event.source.holder is Container && (event.source.holder as Container).customName() == this.title)
            event.isCancelled = true
    }

    @EventHandler
    private fun listener3(event: InventoryCloseEvent) {
        if(event.view.player == player && inventoryIds.contains(inventoryId))
            inventoryIds.remove(inventoryId)
    }

}