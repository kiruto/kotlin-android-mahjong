/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmvp.layout

import com.badlogic.gdx.math.Rectangle
import dev.yuriel.kotmvp.Dev

/**
 * Created by yuriel on 8/7/16.
 */

fun getScreenLayout() = LayoutPosition(0F, 0F, Dev.getDefaultWidth(), Dev.getDefaultHeight())

data class LayoutPosition(var size: LayoutSize, var origin: LayoutOrigin) {
    constructor(x: Float, y: Float, width: Float,height: Float): this(Pair(width, height), Pair(x, y))
    constructor(size: Pair<Float, Float>, origin: Pair<Float, Float>): this(LayoutSize(size), LayoutOrigin(origin))
    constructor(rect: Rectangle): this(Pair(rect.width, rect.height), Pair(rect.x, rect.y))

    var ghostOrigin = Pair<Float?, Float?>(null, null)
        private set
        get() = Pair(field.first?: origin.x, field.second?: origin.y)

    fun correct(relativeX: Float, relativeY: Float) {
        ghostOrigin = Pair(origin.x + relativeX, origin.y + relativeY)
    }

    fun rectangle(): Rectangle = Rectangle(origin.x, origin.y, size.width, size.height)

    fun move(top: Float, right: Float, bottom: Float, left: Float) {
        moveTop(top)
        moveRight(right)
        moveBottom(bottom)
        moveLeft(left)
    }

    infix fun moveTop(top: Float) {
        origin.y += top
    }

    infix fun moveRight(right: Float) {
        origin.x += right
    }

    infix fun moveBottom(bottom: Float) {
        origin.y -= bottom
    }

    infix fun moveLeft(left: Float) {
        origin.x -= left
    }

    infix fun above(other: LayoutPosition) {
        origin.y = other.origin.y + other.size.height
    }

    infix fun below(other: LayoutPosition) {
        origin.y = other.origin.y - size.height
    }

    infix fun toLeftOf(other: LayoutPosition) {
        origin.x = other.origin.x - size.width
    }

    infix fun toRightOf(other: LayoutPosition) {
        origin.x = other.origin.x + other.size.width
    }

    infix fun alignTopOf(other: LayoutPosition) {
        origin.y = other.origin.y + other.size.height - size.height
    }

    infix fun alignBottomOf(other: LayoutPosition) {
        origin.y = other.origin.y
    }

    infix fun alignLeftOf(other: LayoutPosition) {
        origin.x = other.origin.x
    }

    infix fun alignRightOf(other: LayoutPosition) {
        origin.x = other.origin.x + other.size.width - size.width
    }

    infix fun centerHorizontal(other: LayoutPosition) {
        origin.x = other.origin.x + (other.size.width - size.width) / 2F
    }

    infix fun centerVertical(other: LayoutPosition) {
        origin.y = other.origin.y + (other.size.height - size.height) / 2F
    }

    infix fun cutBy(other: LayoutPosition) {
        if (origin.x < other.origin.x) origin.x = other.origin.x
        if (origin.y < other.origin.y) origin.y = other.origin.y
        if (origin.x + size.width > other.origin.x + other.size.width)
            size.width = other.origin.x + other.size.width - origin.x
        if (origin.y + size.height > other.origin.y + other.size.height)
            size.height = other.origin.y + other.size.height - origin.y
    }

    fun setPadding(padding: Float) {
        setPadding(padding, padding, padding, padding)
    }

    fun setPadding(top: Float, right: Float, bottom: Float, left: Float) {
        origin.x += left
        origin.y += bottom
        size.width -= left + right
        size.height -= bottom + top
    }

    fun top(): Float = origin.y + size.height

    fun right(): Float = origin.x + size.width

    fun bottom() = origin.y

    fun left() = origin.x
}

data class LayoutSize(var width: Float, var height: Float) {
    constructor(pair: Pair<Float, Float>): this(pair.first, pair.second)
}

data class LayoutOrigin(var x: Float, var y: Float) {
    constructor(pair: Pair<Float, Float>): this(pair.first, pair.second)
}