package dev.yuriel.kotmahjan.models


/**
 * Created by yuriel on 7/23/16.
 */

open class MahjanException(message: String) : Exception(message) {
    open val advice: String = ""
    override val message: String?
        get() = super.message + ". " + advice
}

class HandsOverFlowException : MahjanException("多牌です")

/**
 * 面子の組が和了の形になっていない場合に投げられます
 */
class IllegalMentsuSizeException(
        /**
         * @return 誤っていると判定されている面子の組を返します
         */
        val mentsuList: List<Mentsu>) : MahjanException("面子の組が和了の形になっていません") {

    override val advice: String
        get() = "面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが" + mentsuList.size + "個の面子が見つかりました"
}

class IllegalShuntsuIdentifierException(private val tile: Hai) : MahjanException("順子識別牌としてありえない牌を検出しました") {

    override val advice: String
        get() {
            val entry = "${tile.id}を識別牌として保存しようとしました\n"
            if (tile.num == -1) {
                return entry + "字牌は順子になりえません"
            }
            return entry + "2番目の牌を順子識別牌とするため、1・9牌は識別牌になりえません"
        }
}

class MahjongTileOverFlowException(//Tile.code
        private val code: Int, //何枚見つかり不正なのか
        private val num: Int) : MahjanException("麻雀の牌は4枚までしかありません") {

    override val advice: String
        get() = "(code = " + code + ")が" + num + "枚見つかりました"
}

class NoSuchTileException(private val id: Int): MahjanException("牌は見つかりません") {
    override val advice: String = "id($id)は 1 から 34 までの数字でなければなりません"
}

class ParseHaiException(private val string: String): MahjanException("牌は見つかりません") {
    override val advice: String = "string = " + string + "は牌に転換できません"
}

class DoraCannotFoundException(private val hai: Hai): MahjanException("ドラが見つかりまえん") {
    override val advice: String = "Hai = " + hai
}

class IllegalIntArrayException(private val size: Int): MahjanException("手牌の形が違います") {
    override val advice: String = "(size = $size)"
}
