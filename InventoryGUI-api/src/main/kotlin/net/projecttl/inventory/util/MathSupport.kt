package net.projecttl.inventory.util

/**
 * Math in inventories
 */
object MathSupport {
    fun toLocation(index: Int): InvLocation {
        return InvLocation(index / 9, index % 9)
    }

    fun toIndex(location: InvLocation): Int {
        return location.y * 9 + location.x
    }
}