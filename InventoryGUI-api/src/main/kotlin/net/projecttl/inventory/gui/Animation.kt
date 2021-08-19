package net.projecttl.inventory.gui

import net.kyori.adventure.text.Component
import net.projecttl.inventory.gui.utils.InventoryType
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.*
import kotlin.collections.HashMap

fun Player.animatedGui(plugin: Plugin, slotType: InventoryType, title: Component, init: Animation.() -> Unit) : Animation {
    val a = Animation(this, slotType, title, plugin).apply(init)
    return a
}

class Animation(val player: Player, val slotType: InventoryType, val title: Component, val plugin: Plugin) {

    lateinit var base: InventoryGuiBuilder

    private var running = false

    lateinit var onStopEvent: () -> Unit

    lateinit var onPlayAnimation: (ticks: Int) -> Unit

    var tick = 0
    var lastAvailableTicks = -1

    // Tick, InventoryGui
    val frames = TreeMap<Int, InventoryGuiBuilder>()
    var taskId: Int = -1

    fun base(init: InventoryGuiBuilder.() -> Unit) {
        base = InventoryGuiBuilder(player, slotType, title, plugin).apply(init)
    }

    fun frame(tick: Int, title: Component = this.title, init: InventoryGuiBuilder.() -> Unit) {
        frames[tick] = InventoryGuiBuilder(player, slotType, title, plugin).apply(init)
    }

    fun onStop(func: () -> Unit) {
        onStopEvent = func
    }

    fun onPlay(func: (ticks: Int) -> Unit) {
        onPlayAnimation = func
    }

    fun start(resetTicks: Boolean = true) {
        if(resetTicks) {
            tick = 0
        }
        running = true
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
            if(running) {
                if(frames.contains(tick)) {
                    lastAvailableTicks = tick
                    val inv = base.build()
                    val slots = frames[tick]?.slots!!
                    inv.apply {
                        for (slot in slots.entries) {
                            setItem(slot.key, slot.value.stack)
                        }
                    }
                    player.openInventory(inv)
                    if(this::onPlayAnimation.isInitialized)
                        onPlayAnimation(tick)
                    if(tick == frames.lastKey())
                        running = false
                }
                tick++
            } else {
                if (taskId != -1) {
                    Bukkit.getScheduler().cancelTask(taskId)
                    if(this::onStopEvent.isInitialized)
                        this.onStopEvent()
                }
            }
        }, 0L, 1L)
    }

    fun stop(closeGui: Boolean = false) {
        if(closeGui && lastAvailableTicks != -1)
            frames[lastAvailableTicks]?.close()
        if(taskId != -1)
            Bukkit.getScheduler().cancelTask(taskId)
    }

}