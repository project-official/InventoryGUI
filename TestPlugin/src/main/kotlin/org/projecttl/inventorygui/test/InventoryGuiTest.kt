package org.projecttl.inventorygui.test

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.projecttl.inventorygui.InventoryType
import org.projecttl.inventorygui.gui

class InventoryGuiTest : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("WELCOME")
        event.player.openInventory(
            gui(InventoryType.CHEST_54, Component.text("TEST")) {
                // dummy slot
                slot(0, ItemStack(Material.STICK))

                slot(1, ItemStack(Material.DEBUG_STICK)) {
                    view.player.sendMessage(Component.text("doing the job", NamedTextColor.GREEN))
                    view.close()
                }
            }
        )
    }

}