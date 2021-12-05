package net.projecttl.inventory

import net.kyori.adventure.text.Component
import net.projecttl.inventory.gui.InventoryBuilder
import net.projecttl.inventory.gui.SimpleInventoryBuilder
import net.projecttl.inventory.gui.LinkedInventoryBuilder
import net.projecttl.inventory.util.InventoryType
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.PluginClassLoader
import java.util.*
import kotlin.collections.HashMap

/**
 * InventoryGUI Data
 */
object InventoryGUI {
    /**
     * A list of registered inventory id
     */
    val inventoryIds = HashMap<UUID, InventoryBuilder>()

    /**
     * The service plugin. Defaults to the plugin that loaded this library. You can modify this later if you want to
     */
    var plugin: JavaPlugin = (javaClass.classLoader as PluginClassLoader).plugin
}

/**
 * Opens the default GUI for a player.
 *
 * @param title The title of the inventory. This is the text shown at the top of the inventory.
 * @param slotType The Inventory's Size. Defaults to 27(3 * 9) if not set.
 * @param init Initialize the inventory builder. Creates a default inventory if not set.
 *
 * @return The built inventory
 */
fun Player.gui(title: Component, slotType: InventoryType = InventoryType.CHEST_27, init: SimpleInventoryBuilder.() -> Unit = {}): Inventory {
    return SimpleInventoryBuilder(this, slotType, title).apply(init).build()
}

/**
 * Opens a linked GUI for a player. Unlike the default GUI, modifying the LinkedInventoryBuilder observes changes to the builder and modifies the inventory on change. Thus, modifications afterwards in the builder will also be applied to the built inventory.
 *
 * @param title The title of the inventory. This is the text shown at the top of the inventory.
 * @param slotType The Inventory's Size. Defaults to 27(3 * 9) if not set.
 * @param init Initialize the inventory builder. Creates a default inventory if not set.
 *
 * @return The built inventory
 */
fun Player.linkedGui(title: Component, slotType: InventoryType = InventoryType.CHEST_27, init: LinkedInventoryBuilder.() -> Unit = {}): Inventory {
    return LinkedInventoryBuilder(this, slotType, title).apply(init).build()
}

/**
 * Creates an animated GUI. Uses the default GUI.
 *
 * @param title The title of the inventory. This is the text shown at the top of the inventory.
 * @param slotType The Inventory's Size. Defaults to 27(3 * 9) if not set.
 * @param init Initialize the inventory builder. Creates a default inventory if not set.
 *
 * @return The built animation
 */
fun Player.animatedGui(title: Component, slotType: InventoryType = InventoryType.CHEST_27, init: Animation.() -> Unit = {}) : Animation {
    return Animation(this, slotType, title).apply(init)
}