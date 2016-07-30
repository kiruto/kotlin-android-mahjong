package dev.yuriel.kotmahjan.rules.yaku.yakuimpl

import dev.yuriel.kotmahjan.models.HaiType
import dev.yuriel.kotmahjan.models.Shuntsu
import dev.yuriel.kotmahjan.rules.MentsuSupport
import java.util.*

/**
 * Created by yuriel on 7/24/16.
 * 一気通貫判定
 * 同種の数牌で123・456・789と揃えると成立
 */
fun 一気通貫Impl(s: MentsuSupport): Boolean {
    if (s.shuntsuCount < 3) {
        return false
    }

    val manzu = ArrayList<Shuntsu>(4)
    val sohzu = ArrayList<Shuntsu>(4)
    val pinzu = ArrayList<Shuntsu>(4)

    //各タイプに振り分ける
    for (shuntsu in s.getShuntsuList()) {
        val type = shuntsu.getHai()?.type
        if (type == HaiType.MZ) {
            manzu.add(shuntsu)
        } else if (type == HaiType.PZ) {
            pinzu.add(shuntsu)
        } else if (type == HaiType.SZ) {
            sohzu.add(shuntsu)
        }
    }

    /**
     * 123 456 789が全て含まれるかを判定します
     * 例えば萬子の順子のみが含まれる場合に正しく動作します
     * 逆に、萬子123 筒子 456 789の場合もtrueになってしまいます

     * @param oneTypeShuntsuList 単一のタイプの順子リスト
     * *
     * @return 123 456 789が全て含まれるか
     */
    fun isIkkitsukan(oneTypeShuntsuList: List<Shuntsu>): Boolean {
        //この3つが全てtrueになれば一気通貫
        var number2 = false
        var number5 = false
        var number8 = false

        for (shuntsu in oneTypeShuntsuList) {
            val num = shuntsu.getHai()?.num
            if (num == 2) {
                number2 = true
            } else if (num == 5) {
                number5 = true
            } else if (num == 8) {
                number8 = true
            }
        }
        return number2 && number5 && number8
    }

    if (manzu.size >= 3) {
        return isIkkitsukan(manzu)
    }
    if (sohzu.size >= 3) {
        return isIkkitsukan(sohzu)
    }
    if (pinzu.size >= 3) {
        return isIkkitsukan(pinzu)
    }
    return false
}