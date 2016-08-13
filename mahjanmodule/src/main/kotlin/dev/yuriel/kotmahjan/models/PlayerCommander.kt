/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.models

import rx.Observable

/**
 * Created by yuriel on 7/31/16.
 */
interface PlayerCommander {
    fun receive(hai: Hai)
    fun da(basis: List<Hai>): Hai
    fun kan(haiList: List<Hai>): Boolean
    fun pon(haiList: List<Hai>): Boolean
    fun chi(haiList: List<Hai>): Boolean
    fun ron(haiList: List<Hai>): Boolean
    fun getBasisByVisibleHai(visible: List<Hai>): List<Hai>
    fun getObservable(event: RoundEvent, duration: Long): Observable<RoundEvent>
}