/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 二盃口判定
 * 一盃口が２つ含まれる場合に成立
 * 一盃口とは複合しない
 */
fun 二盃口Impl(s: MentsuSupport): Boolean = peiko(s.getShuntsuList()) == 2