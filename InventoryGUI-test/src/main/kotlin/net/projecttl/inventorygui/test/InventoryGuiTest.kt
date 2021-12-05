package net.projecttl.inventorygui.test

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.projecttl.inventory.animatedGui
import net.projecttl.inventory.gui
import net.projecttl.inventory.util.InventoryType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random

class InventoryGuiTest : JavaPlugin(), Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
        Bukkit.getPlayer("")?.gui(text("")) {
            slot(1, ItemStack(Material.IRON_INGOT)) {
                isCancelled = false
            }
        }
    }

    @EventHandler
    fun chat(event: AsyncChatEvent) {
        if(PlainTextComponentSerializer.plainText().serialize(event.message()).contains("animation")) {
            println(PlainTextComponentSerializer.plainText().serialize(event.message()))
            val anim = event.player.animatedGui(text("Hi", NamedTextColor.GREEN)) {
                base {
                    slot(0, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(1, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(2, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(3, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(4, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(5, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(6, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(7, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(8, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(9, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(10, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(11, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(15, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(16, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(17, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(18, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(19, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(20, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(21, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(22, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(23, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(24, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(25, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                    slot(26, ItemStack(Material.WHITE_STAINED_GLASS_PANE))
                }

                frame(0) {
                    slot(12, ItemStack(Material.GOLDEN_APPLE))
                    slot(13, ItemStack(Material.DIRT))
                    slot(14, ItemStack(Material.IRON_INGOT))
                }

                for(i in 0 until 50) {
                    frame(i * (if(i <= 20) 2 else if(i <= 10) 4 else if(i <= 10) 8 else 16)) {
                        val rand = Random.nextInt(0, 5)
                        val rand2 = Random.nextInt(0, 5)
                        val rand3 = Random.nextInt(0, 5)
                        slot(12, ItemStack(if(rand == 0) Material.GOLDEN_APPLE else if(rand == 1) Material.IRON_INGOT else Material.DIRT))
                        slot(13, ItemStack(if(rand2 == 0) Material.GOLDEN_APPLE else if(rand2 == 1) Material.IRON_INGOT else Material.DIRT))
                        slot(14, ItemStack(if(rand3 == 0) Material.GOLDEN_APPLE else if(rand3 == 1) Material.IRON_INGOT else Material.DIRT))
                    }
                }
            }
            anim.start()
        }
    }

}