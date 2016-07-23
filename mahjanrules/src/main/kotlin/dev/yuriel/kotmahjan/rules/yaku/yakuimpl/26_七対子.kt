package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 七対子判定クラス
 * 対子のみで構成された場合成立
 */
fun 七対子Impl(s: MentsuSupport): Boolean = s.toitsuCount == 7