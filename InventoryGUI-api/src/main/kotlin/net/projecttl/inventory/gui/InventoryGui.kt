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
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

val inventoryIds = HashMap<UUID, InventoryGuiBuilder>()

fun Player.gui(plugin: JavaPlugin, slotType: InventoryType, title: Component, init: InventoryGuiBuilder.() -> Unit) {
    val a = InventoryGuiBuilder(player!!, slotType, title, plugin)
    a.apply(init).build()
}

class InventoryGuiBuilder(private val player: Player, private val slotType: InventoryType, private val title: Component, private val plugin: JavaPlugin) : Listener {

    val slots = hashMapOf<Int, Slot>()
    private val inventoryId = UUID.randomUUID()
    private lateinit var inv: Inventory

    init {
        inventoryIds[inventoryId] = this
    }

    fun slot(slot: Int, item: ItemStack, ignoreClick: Boolean = true, handler: InventoryClickEvent.() -> Unit) {
        slots[slot] = Slot(item, ignoreClick, handler)
    }

    fun slot(slot: Int, item: ItemStack, ignoreClick: Boolean = true) {
        slot(slot, item, ignoreClick) {}
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
                        if (slot.key == event.rawSlot){
                            slot.value.click(event)
                            event.isCancelled = slot.value.ignoreClick
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private fun listener2(event: InventoryMoveItemEvent) {
        if (inventoryIds.contains(inventoryId) && event.source.holder?.inventory?.viewers?.contains(player)!!
            && event.source.holder is Container && (event.source.holder as Container).customName() == this.title)
                event.isCancelled = true
    }

    @EventHandler
    private fun listener3(event: InventoryCloseEvent) {
        if(event.view.player == player && inventoryIds.contains(inventoryId))
            inventoryIds.remove(inventoryId)
    }

}
