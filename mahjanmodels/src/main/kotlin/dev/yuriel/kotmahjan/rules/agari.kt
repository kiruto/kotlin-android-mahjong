package dev.yuriel.kotmahjan.rules

import dev.yuriel.kotmahjan.models.Hai
import dev.yuriel.kotmahjan.models.PlayerContext
import dev.yuriel.kotmahjan.models.RoundContext
import dev.yuriel.kotmahjan.models.isSanGen
import dev.yuriel.kotmahjan.rules.Score.*
import dev.yuriel.kotmahjan.rules.yaku.*
import java.util.*

/**
 * Created by yuriel on 7/23/16.
 * 和了判定に関するクラスです。
 * 役の判定は別のクラスで行うがここから呼び出します
 */
class Agari(private var hands: Hands? = null,
            private val roundContext: RoundContext? = null,
            private val playerContext: PlayerContext? = null) {

    //付いた役満リスト
    private var yakumanList: MutableList<Yaku> = ArrayList(1)

    //付いた通常役リスト
    private var normalYakuList: MutableList<Yaku> = ArrayList(0)

    //その時の面子の組
    private var support: MentsuSupport? = null

    // 翻
    var han = 0
        private set
    // 符
    var fu = 0
        private set
    // 点数
    var score = SCORE0
        private set

    fun getYakumanList(): List<Yaku> {
        return yakumanList
    }

    fun getNormalYakuList(): List<Yaku> {
        return normalYakuList
    }

    fun calculate() {
        //和了れない場合は即座に終了
        if (!hands!!.canWin) return

        //国士無双の場合はそれ以外ありえないので保存して即座に終了
        if (hands!!.isKokushimuso) {
            yakumanList.add(国士無双)
            return
        }

        //役満を探し見つかれば通常役は調べずに終了
        if (findYakuman()) {
            if (playerContext == null) {
                score = SCORE0
                return
            }
            score = Score.calculateYakumanScore(playerContext.isParent(), yakumanList.size)
            return
        }

        findNormalYaku()
    }

    /**
     * @return 役満が見つかったか
     */
    private fun findYakuman(): Boolean {
        //役満をストックしておき、見つかったら先にこちらに保存する
        val yakumanStock = ArrayList<Yaku>(4)

        //それぞれの面子の完成形で判定する
        for (comp in hands!!.getMentsuCompSet()) {
            //val yakumanResolverSet = Mahjong4jYakuConfig.getYakumanResolverSet(support, roundContext, playerContext)

            for ((name, yaku) in yakuMan) {
                if (yaku is MatchableYaku && yaku.isMatch(comp)) {
                    yakumanStock.add(yaku)
                } else if (yaku is ContextYaku && yaku.isMatch(roundContext, playerContext, comp)) {
                    yakumanStock.add(yaku)
                }
            }

            //ストックと保存する役満リストと比較し大きい方を保存する
            if (yakumanList.size < yakumanStock.size) {
                yakumanList = yakumanStock
                this.support = comp
            }
        }

        return yakumanList.size > 0
    }

    private fun findNormalYaku() {
        //それぞれの面子の完成形で判定する
        for (comp in hands!!.getMentsuCompSet()) {
            //役をストックしておく
            val yakuStock = ArrayList<Yaku>(7)
            //val resolverSet = Mahjong4jYakuConfig.getNormalYakuResolverSet(support, roundContext, playerContext)
            for ((name, yaku) in normalYaku) {
                if (yaku is MatchableYaku && yaku.isMatch(comp)) {
                    yakuStock.add(yaku)
                } else if (yaku is ContextYaku && yaku.isMatch(roundContext, playerContext, comp)) {
                    yakuStock.add(yaku)
                }
            }

            val hanSum = calcHanSum(yakuStock)
            if (hanSum > this.han) {
                han = hanSum
                normalYakuList = yakuStock
                this.support = comp
            }
        }

        if (han > 0) {
            calcDora(hands!!.handsComp, roundContext, normalYakuList.contains(リーチ))
        }
        calcScore()
    }

    private fun calcScore() {
        fu = calcFu(support)
        if (playerContext == null) {
            return
        }
        score = Score.calculateScore(playerContext.isParent(), han, fu)
    }

    /**
     * 符計算をします
     * 役なしの場合は0
     * Situationが無い場合は一律で20
     */
    private fun calcFu(comp: MentsuSupport?): Int {
        if (normalYakuList.size == 0) {
            return 0
        }
        if (playerContext == null || roundContext == null) {
            return 20
        }
        // 特例の平和ツモと七対子の符
        if (normalYakuList.contains(平和) && normalYakuList.contains(門前清模和)) {
            return 20
        }
        if (normalYakuList.contains(七対子)) {
            return 25
        }

        var tmpFu = 20
        // 門前ロンなら+10
        tmpFu += calcFuByAgari()

        // 各メンツの種類による加符
        for (mentsu in comp!!.allMentsu) {
            tmpFu += mentsu.fu
        }

        tmpFu += calcFuByWait(comp, hands!!.last)
        tmpFu += calcFuByJanto()

        return tmpFu
    }

    /**
     * 雀頭の種類による加符
     * 連風牌の場合は+4とします

     * @return
     */
    private fun calcFuByJanto(): Int {
        val jantoTile = support!!.janto?.getHai()
        var tmp = 0
        if (jantoTile == roundContext!!.getBakaze()) {
            tmp += 2
        }
        if (jantoTile == playerContext!!.getJikaze()) {
            tmp += 2
        }
        if (isSanGen(jantoTile)) {
            tmp += 2
        }
        return tmp
    }

    private fun calcFuByAgari(): Int {
        // ツモ
        if (playerContext!!.isTsumo()) {
            return 2
        }
        // 門前ロン
        if (!hands!!.isOpen) {
            return 10
        }
        return 0
    }

    /**
     * 待ちの種類による可符
     * @param comp
     * @param last
     * @return
     */
    private fun calcFuByWait(comp: MentsuSupport, last: Hai?): Int {
        if (last != null && (comp.isKanchan(last) || comp.isPenchan(last) || comp.isTanki(last))) {
            return 2
        }

        return 0
    }

    private fun calcDora(handsComp: IntArray, generalSituation: RoundContext?, isReach: Boolean) {
        if (generalSituation == null) {
            return
        }
        var dora = 0
        for (tile in generalSituation.getDora()) {
            dora += handsComp[tile.getCode()]
        }
        for (i in 0..dora - 1) {
            normalYakuList.add(ドラ)
            han += ドラ.han
        }

        if (isReach) {
            var uradora = 0
            for (tile in generalSituation.getUradora()) {
                uradora += handsComp[tile.getCode()]
            }
            for (i in 0..uradora - 1) {
                normalYakuList.add(裏ドラ)
                han += 裏ドラ.han
            }
        }
    }

    /**
     * 手牌が食い下がる形かも判定し、翻の合計を計算し、返します

     * @param yakuStock 役のストック
     * *
     * @return 翻数の合計
     */
    private fun calcHanSum(yakuStock: List<Yaku>): Int {
        var hanSum = 0
        if (hands!!.isOpen) {
            for (yaku in yakuStock) {
                hanSum += yaku.kuisagari
            }
        } else {
            for (yaku in yakuStock) {
                hanSum += yaku.han
            }
        }
        return hanSum
    }
}
