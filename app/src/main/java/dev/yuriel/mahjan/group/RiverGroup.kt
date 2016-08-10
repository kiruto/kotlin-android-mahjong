package dev.yuriel.mahjan.group

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmvp.bases.BaseGroup
import dev.yuriel.mahjan.model.TileWrapper

/**
 * Created by yuriel on 8/9/16.
 */
class RiverGroup(): BaseGroup() {

    private val lines: Array<TileHorizontalGroup>

    init {
        lines = Array(3) { i ->
            val result = TileHorizontalGroup(mutableListOf(), i)
            addActor(result)
            result
        }
    }

    fun update(tile: List<Hai>) {
        val list = Array(tile.size) { i -> TileWrapper(tile[i]) }.toList()
        addTiles(list)
    }

    private fun addTiles(tile: List<TileWrapper>) {
        var index = -1
        var subList: MutableList<TileWrapper>? = null
        for (i in 0..tile.size - 1) {
            if (i % 6 == 0) {
                if (index > -1 && index < 2 && subList!= null) {
                    lines[index].update(subList)
                }
                if (index < 2) {
                    index++
                    subList = mutableListOf()
                }
            }
            subList?.add(tile[i])
        }
        if (subList != null) {
            lines[index].update(subList)
        }
        calculate()
    }

    fun calculate() {
        var originY = 0F
        for (i in lines) {
            i.setPosition(0F, originY)
            originY -= i.height
        }
    }

    override fun destroy() {
        for (i in lines) {
            i.destroy()
        }
    }

    private fun TileHorizontalGroup.isFull(): Boolean = size == 6
}