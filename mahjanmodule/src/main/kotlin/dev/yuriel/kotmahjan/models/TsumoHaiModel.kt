package dev.yuriel.kotmahjan.models

import kotlin.properties.Delegates

/**
 * Created by yuriel on 8/13/16.
 */
class TsumoHaiModel {

    private val listeners = mutableMapOf<Int, (Hai?) -> Unit>()
    var hai: Hai? by Delegates.observable<Hai?>(null) { prop, old, new ->
        notifyDataChange(new)
    }

    fun listen(id: Int, listener: (Hai?) -> Unit) {
        listeners.put(id, listener)
    }

    private fun notifyDataChange(h: Hai?) {
        for ((id, l) in listeners) {
            l.invoke(h)
        }
    }
}