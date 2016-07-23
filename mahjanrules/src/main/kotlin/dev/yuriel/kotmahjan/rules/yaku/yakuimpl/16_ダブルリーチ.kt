package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 */
fun ダブルリーチImpl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport): Boolean = r != null && p != null && p.isDoubleReach() && r.isRich()