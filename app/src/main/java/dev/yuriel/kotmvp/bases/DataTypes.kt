package dev.yuriel.kotmvp.bases

import com.badlogic.gdx.math.Rectangle
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/7/16.
 */

fun getScreenLayout() = LayoutPosition(0F, 0F, Dev.getDefaultWidth(), Dev.getDefaultHeight())
fun getOperateScreenLayout() {}

data class LayoutPosition(var size: LayoutSize, var origin: LayoutOrigin) {
    constructor(x: Float, y: Float, width: Float,height: Float): this(Pair(width, height), Pair(x, y))
    constructor(size: Pair<Float, Float>, origin: Pair<Float, Float>): this(LayoutSize(size), LayoutOrigin(origin))
    constructor(rect: Rectangle): this(Pair(rect.width, rect.height), Pair(rect.x, rect.y))

    fun rectangle(): Rectangle = Rectangle(origin.x, origin.y, size.width, size.height)

    fun above(other: LayoutPosition) {
        origin.y = other.origin.y + other.size.height
    }

    fun below(other: LayoutPosition) {
        origin.y = other.origin.y - size.height
    }

    fun toLeftOf(other: LayoutPosition) {
        origin.x = other.origin.x - size.width
    }

    fun toRightOf(other: LayoutPosition) {
        origin.x = other.origin.x + other.size.width
    }

    fun alignTopOf(other: LayoutPosition) {
        origin.y = other.origin.y + other.size.height - size.height
    }

    fun alignBottomOf(other: LayoutPosition) {
        origin.y = other.origin.y
    }

    fun alignLeftOf(other: LayoutPosition) {
        origin.x = other.origin.x
    }

    fun alignRightOf(other: LayoutPosition) {
        origin.x = other.origin.x + other.size.width - size.width
    }

    fun centerHorizontal(other: LayoutPosition) {
        origin.x = other.origin.x + (other.size.width - size.width) / 2F
    }

    fun centerVertical(other: LayoutPosition) {
        origin.y = other.origin.y + (other.size.height - size.height) / 2F
    }

    fun cutBy(other: LayoutPosition) {
        if (origin.x < other.origin.x) origin.x = other.origin.x
        if (origin.y < other.origin.y) origin.y = other.origin.y
        if (origin.x + size.width > other.origin.x + other.size.width)
            size.width = other.origin.x + other.size.width - origin.x
        if (origin.y + size.height > other.origin.y + other.size.height)
            size.height = other.origin.y + other.size.height - origin.y
    }

    fun setPadding(padding: Float) {
        origin.x += padding
        origin.y += padding
        size.width -= padding * 2
        size.height -= padding * 2
    }

    fun top(): Float {
        return origin.y + size.height
    }

    fun right(): Float {
        return origin.x + size.width
    }
}

data class LayoutSize(var width: Float, var height: Float) {
    constructor(pair: Pair<Float, Float>): this(pair.first, pair.second)
}

data class LayoutOrigin(var x: Float, var y: Float) {
    constructor(pair: Pair<Float, Float>): this(pair.first, pair.second)
}