package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.rules.MentsuSupport

/**
 * Created by yuriel on 7/24/16.
 * 最後の牌でロン和了した場合に成立
 */
fun 海底撈月Impl(r: RoundContext?, p: PlayerContext?, s: MentsuSupport): Boolean = r != null && p != null && r.isHoutei() && p.isTsumo()