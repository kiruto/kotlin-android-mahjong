/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

/**
 * Created by yuriel on 8/13/16.
 */
interface LoopContext {
    fun onStart()

    fun onRichi()
    fun onChi()
    fun onPon()
    fun onKan()
    fun onRon()

    /**
     * ツモる時
     */
    fun tsumo()
    fun tsumoAfterKan()

    /**
     * 牌を捨てる時
     */
    fun sute()

    /**
     * 全て完了
     */
    fun onEnd()

    /**
     * 九種九牌
     */
    fun is9x9Hai(): Boolean

    fun couldRichi(): Boolean
    fun couldChi(): Boolean
    fun couldPon(): Boolean
    fun couldKan(): Boolean
    fun couldRon(): Boolean
    fun hasAction(): Boolean {
        return couldChi() || couldPon() || couldKan() || couldRon()
    }

    fun waitForAction()
    fun getAction(): RoundEvent
}