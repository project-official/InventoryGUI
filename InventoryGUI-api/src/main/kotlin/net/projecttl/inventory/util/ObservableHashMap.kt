package net.projecttl.inventory.util

/**
 * An observable type of hashmaps. Triggers observers on data input.
 */
class ObservableHashMap<K, V>: HashMap<K, V>() {
    private val observers = ArrayList<(Pair<K, V>) -> Unit>()

    fun trigger(key: K, value: V) {
        observers.forEach {
            it.invoke(key to value)
        }
    }

    fun addObserver(observer: (Pair<K, V>) -> Unit) {
        observers.add(observer)
    }

    override fun put(key: K, value: V): V? {
        val result = super.put(key, value)
        trigger(key, value)
        return result
    }
}