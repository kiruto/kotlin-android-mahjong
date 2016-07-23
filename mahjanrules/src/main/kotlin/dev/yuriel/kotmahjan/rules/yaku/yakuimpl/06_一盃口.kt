package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 一盃口判定クラス
 * 223344など、同種同数の順子が2組ある場合に成立
 * 二盃口の場合は二盃口のみとなる
 */
fun 一盃口Impl(s: MentsuSupport): Boolean = peiko(s.getShuntsuList()) == 1