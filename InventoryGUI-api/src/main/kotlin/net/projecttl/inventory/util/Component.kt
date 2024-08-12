package net.projecttl.inventory.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

fun String.toMini(): Component = MiniMessage.miniMessage().deserialize(this)
fun Component.toOriginal(): String = MiniMessage.miniMessage().serialize(this)

fun Component.compareTo(comp: Component): Boolean {
	val original = PlainTextComponentSerializer.plainText().serialize(this)
	val target = PlainTextComponentSerializer.plainText().serialize(comp)

	return original.contains(target)
}
