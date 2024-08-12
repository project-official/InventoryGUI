package net.projecttl.inventory.gui

import net.kyori.adventure.text.Component
import net.projecttl.inventory.InventoryGUI.inventoryIds
import net.projecttl.inventory.InventoryGUI.plugin
import net.projecttl.inventory.util.InventoryType
import net.projecttl.inventory.util.Slot
import net.projecttl.inventory.util.compareTo
import org.bukkit.Bukkit
import org.bukkit.block.Container
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

class SimpleInventoryBuilder(
    override val player: Player,
    override val slotType: InventoryType,
    override val title: Component
) : Listener, InventoryBuilder {
    override val slots = HashMap<Int, Slot>()
    val closeHandlers = ArrayList<InventoryCloseEvent.() -> Unit>()

    @Suppress("WeakerAccess")
    override val id: UUID = UUID.randomUUID()
    override lateinit var inventory: Inventory
        private set

    init {
        inventoryIds[id] = this
    }

    override fun slot(slot: Int, item: ItemStack, handler: InventoryClickEvent.() -> Unit) {
        slots[slot] = Slot(item, handler)
    }

    override fun slot(slot: Int, item: ItemStack) {
        slot(slot, item) {}
    }
    
    override fun onClose(handler: InventoryCloseEvent.() -> Unit) {
        closeHandlers.add(handler)
    }

    override fun close() {
        if(this::inventory.isInitialized)
            inventory.close()
    }

    override fun build() : Inventory {
        inventory = Bukkit.createInventory(null, slotType.size, title)
        for (slot in slots.entries) {
            inventory.setItem(slot.key, slot.value.stack)
        }
        player.openInventory(inventory)
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
        return inventory
    }

    @EventHandler
    private fun InventoryClickEvent.listener() {
        if (title.compareTo(this.view.title())) {
            if (inventoryIds.contains(id) && this.currentItem != null && this.view.player == player) {
                if (this.inventory == inventory) {
                    for (slot in slots.entries) {
                        if (slot.key == this.rawSlot){
                            this.isCancelled = true
                            slot.value.click(this)
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private fun InventoryMoveItemEvent.listener2() {
        if (inventoryIds.contains(id) && this.source.holder?.inventory?.viewers?.contains(player)!!
            && this.source.holder is Container && (this.source.holder as Container).customName() == title)
                this.isCancelled = true
    }

    @EventHandler
    private fun InventoryCloseEvent.listener3() {
        for(closeHandler in closeHandlers)
            closeHandler(this)
        if(this.view.player == player && inventoryIds.contains(id))
            inventoryIds.remove(id)
    }

    @EventHandler
    private fun PlayerSwapHandItemsEvent.listener4() {
        if (this.player.inventory == inventory) {
            this.isCancelled = true
        }
    }

    override fun destroy() {
        if (player.openInventory.topInventory == inventory) {
            player.closeInventory()
        }
    }
}
