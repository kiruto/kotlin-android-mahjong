package dev.yuriel.kotmahjan.models

import java.util.*

/**
 * Created by yuriel on 7/16/16.
 */

class Yama(val haiList: MutableList<Hai>) {

}

class Kawa {
    private val haiList: MutableList<Hai> by lazy { ArrayList<Hai>() }

    fun push(hai: Hai) = haiList.add(hai)

    fun pop(): Hai {
        val hai = haiList.get(haiList.size - 1)
        haiList.remove(hai)
        return hai
    }

    fun get(): MutableList<Hai> = haiList

    fun clear() {
        haiList.clear()
    }
}

class Tehai: Comparator<Hai> {

    companion object {
        fun fromString(string: String): Tehai {
            val list: MutableList<Hai> = mutableListOf()
            if (!string.isEmpty()) {
                val haiStrings = string.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (haiString in haiStrings) {
                    list.add(Hai.parse(haiString))
                }
            }
            val result = Tehai()
            result.put(list)
            return result
        }
    }

    val haiList: MutableList<Hai> by lazy {
        ArrayList<Hai>()
    }

    fun put(hai: Hai) {
        haiList.add(hai)
    }

    fun put(list: Collection<Hai>) {
        haiList.addAll(list)
    }

    fun clear() {
        haiList.clear()
    }

    fun enough(): Boolean {
        return haiList.size > 12
    }

    fun sort() {
        //val result: ArrayList<Hai> = ArrayList()
        haiList.sortWith(this)
        val resultArray: Array<ArrayList<Hai>> = Array(HaiType.values().size, { i -> ArrayList<Hai>() })
        for (hai: Hai in haiList) {
            for (t: HaiType in HaiType.values()) {
                if (hai.type == t) {
                    resultArray[t.ordinal].add(hai)
                    continue
                }
            }
        }
        haiList.clear()
        for (list in resultArray) {
            haiList.addAll(list)
        }
    }

    /**
     * [0,0,0,0,0,0,0,0,0,  MZ
     *  0,0,0,0,0,0,0,0,0,  PZ
     *  0,0,0,0,0,0,0,0,0,  SZ
     *  0,0,0,0,0,0,0]      TSUHAI
     */
    fun toTypedArray(removeFuro:Boolean = true): IntArray {
        val result = IntArray(34) { i -> 0 }
        for (hai in haiList) {
            if (removeFuro && hai.hasStatus(STATUS_FURO)) continue
            result[hai.id - 1]++
        }
        return result
    }

    override fun toString(): String {
        var result = ""
        for (h in haiList) {
            result += h.toString() + ","
        }
        return result.substring(0, result.length - 1)
    }

    override fun compare(hai1: Hai, hai2: Hai): Int {
        return hai1.num - hai2.num
    }
}

abstract class Mentsu {
    /**
     * どの牌で面子となっているか
     * 順子の場合は2番目の牌です
     */
    abstract var identifierTile: Hai?

    /**
     * 面子として成立している場合true
     * 面子として成立していない場合false
     */
    var isMentsu: Boolean = false

    /**
     * 鳴いているか
     *
     * 明X子の場合はtrue
     * 暗X子の場合はfalse
     * 食い下がり判定用です
     * 鳴いている場合はtrueですが、
     * 暗槓の場合はfalseです。
     */
    var isOpen: Boolean = false

    /**
     * 符計算用
     * @return 各面子での加算符
     */
    abstract val fu: Int

    fun getHai(): Hai? {
        return identifierTile
    }
}

/**
 * 槓子に関するクラスです
 * 暗槓と明槓の両方を扱います
 */
class Kantsu(override var identifierTile: Hai?): Mentsu() {

    /**
     * 槓子が完成していることを前提にしているため
     * 槓子であるかのチェックは伴いません。
     * @param isOpen         暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param identifierTile どの牌の槓子なのか
     */
    constructor(isOpen: Boolean, identifierTile: Hai): this(identifierTile) {
        this.isOpen = isOpen
        this.isMentsu = true
    }

    /**
     * 槓子であるかのチェックも伴います
     * すべての牌(t1~4)が同じ場合にisMentsuがtrueになります
     * @param isOpen 暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     * @param tile4  4枚目
     */
    constructor(isOpen: Boolean, tile1: Hai, tile2: Hai, tile3: Hai, tile4: Hai): this(tile1) {
        this.isOpen = isOpen
        this.isMentsu = check(tile1, tile2, tile3, tile4)
        if (!isMentsu) {
            identifierTile = null
        }
    }

    override val fu: Int by lazy {
        var mentsuFu = 8
        if (!isOpen) {
            mentsuFu *= 2
        }
        if (identifierTile?.isYaochu() == true) {
            mentsuFu *= 2
        }
        mentsuFu
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Kantsu) return false

        if (isMentsu !== o.isMentsu) return false
        if (isOpen !== o.isOpen) return false
        return identifierTile === o.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (null != identifierTile) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        result = 31 * result + if (isOpen) 1 else 0
        return result
    }

    companion object {

        /**
         * t1~4が同一の牌かを調べます
         * @param tile1 1枚目
         * @param tile2 2枚目
         * @param tile3 3枚目
         * @param tile4 4枚目
         * @return 槓子の場合true 槓子でない場合false
         */
        fun check(tile1: Hai, tile2: Hai, tile3: Hai, tile4: Hai): Boolean {
            return tile1 sameAs tile2 && tile2 sameAs tile3 && tile3 sameAs tile4
        }
    }
}

/**
 * 刻子に関するクラスです
 * 暗刻と明刻の両方を扱います
 */
class Kotsu(override var identifierTile: Hai?) : Mentsu() {

    /**
     * 刻子であることがわかっている場合に利用します
     * @param isOpen         暗刻ならばfalse 明刻ならばtrue
     * @param identifierTile どの牌の刻子なのか
     */
    constructor(isOpen: Boolean, identifierTile: Hai?): this(identifierTile) {
        this.isOpen = isOpen
        this.isMentsu = true
    }

    /**
     * 刻子であるかのチェックも伴います
     * すべての牌(t1~3)が同じ場合にisMentsuがtrueになります
     * @param isOpen 暗刻の場合false, 明刻の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     */
    constructor(isOpen: Boolean, tile1: Hai, tile2: Hai, tile3: Hai): this(tile1) {
        this.isOpen = isOpen
        this.isMentsu = check(tile1, tile2, tile3)
        if (!isMentsu) {
            identifierTile = null
        }
    }

    override val fu: Int
        get() {
            var mentsuFu = 2
            if (!isOpen) {
                mentsuFu *= 2
            }
            if (identifierTile?.isYaochu() == true) {
                mentsuFu *= 2
            }
            return mentsuFu
        }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Kotsu) return false

        if (isMentsu !== o.isMentsu) return false
        if (isOpen !== o.isOpen) return false
        return identifierTile === o.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (identifierTile != null) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        result = 31 * result + if (isOpen) 1 else 0
        return result
    }

    companion object {

        /**
         * 刻子であるかの判定を行ないます
         * @param tile1 1枚目
         * @param tile2 2枚目
         * @param tile3 3枚目
         * @return 刻子であればtrue 刻子でなければfalse
         */
        fun check(tile1: Hai, tile2: Hai, tile3: Hai): Boolean {
            return tile1 sameAs tile2 && tile2 sameAs tile3
        }
    }
}

/**
 * 順子に関するクラスです
 * 暗順子と明順子の両方を扱います
 */
class Shuntsu(override var identifierTile: Hai?) : Mentsu() {

    /**
     * 順子であることがわかっている場合に利用します
     * @param isOpen         明順子ならばtrue 暗順子ならばfalse
     * @param identifierTile 順子の組の二番目
     */
    @Throws(IllegalShuntsuIdentifierException::class)
    constructor(isOpen: Boolean, identifierTile: Hai): this(identifierTile) {
        setIdTile(identifierTile)
        this.isOpen = isOpen
        this.isMentsu = true
    }

    /**
     * 順子であるかの判定も伴います
     * @param isOpen 明順子ならばtrue 暗順子ならばfalseを入力して下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     */
    constructor(isOpen: Boolean, tile1: Hai, tile2: Hai, tile3: Hai): this(tile2) {
        var t1 = tile1
        var t2 = tile2
        var t3 = tile3
        this.isOpen = isOpen

        // TODO: 共通化
        //ソートする
        var s: Hai
        if (t1.num > t2.num) {
            s = t1
            t1 = t2
            t2 = s
        }
        if (t1.num > t3.num) {
            s = t1
            t1 = t3
            t3 = s
        }
        if (t2.num > t3.num) {
            s = t2
            t2 = t3
            t3 = s
        }
        this.isMentsu = check(t1, t2, t3)
        if (!isMentsu) {
            identifierTile = null
        }
    }

    @Throws(IllegalShuntsuIdentifierException::class)
    private fun setIdTile(identifierTile: Hai) {
        if (identifierTile.isYaochu()) {
            throw IllegalShuntsuIdentifierException(identifierTile)
        }
        this.identifierTile = identifierTile
    }

    override val fu: Int
        get() = 0

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Shuntsu) return false

        if (isMentsu !== o.isMentsu) return false
        if (isOpen !== o.isOpen) return false
        return identifierTile === o.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (identifierTile != null) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        result = 31 * result + if (isOpen) 1 else 0
        return result
    }

    companion object {

        /**
         * 順子かどうかの判定を行ないます
         * @param t1 1枚目
         * @param t2 2枚目
         * @param t3 3枚目
         * @return 順子であればtrue 順子でなければfalse
         */
        fun check(t1: Hai, t2: Hai, t3: Hai): Boolean {
            var tile1 = t1
            var tile2 = t2
            var tile3 = t3

            //Typeが違う場合false
            if (tile1.type != tile2.type || tile2.type != tile3.type) {
                return false
            }

            //字牌だった場合false
            if (tile1.num == -1 || tile2.num == -1 || tile3.num == -1) {
                return false
            }

            //ソートする
            var s: Hai
            if (tile1.num > tile2.num) {
                s = tile1
                tile1 = tile2
                tile2 = s
            }
            if (tile1.num > tile3.num) {
                s = tile1
                tile1 = tile3
                tile3 = s
            }
            if (tile2.num > tile3.num) {
                s = tile2
                tile2 = tile3
                tile3 = s
            }

            //判定
            return tile1.num + 1 == tile2.num && tile2.num + 1 == tile3.num
        }
    }
}

class Toitsu(override var identifierTile: Hai?) : Mentsu() {

    /**
     * 対子であることがわかっている場合に使います
     * @param identifierTile 対子の種類
     */
    init {
        this.isMentsu = true
    }

    /**
     * 対子であるかのチェックを伴います
     * @param tile1 1枚目
     * @param tile2 2枚目
     */
    constructor(tile1: Hai, tile2: Hai): this(tile1) {
        isMentsu = Toitsu.check(tile1, tile2)
        if (!isMentsu) {
            this.identifierTile = null
        }
    }

    override val fu: Int
        get() = 0

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Toitsu) return false

        if (isMentsu !== o.isMentsu) return false
        return identifierTile === o.identifierTile

    }

    override fun hashCode(): Int {
        var result: Int = if (identifierTile != null) identifierTile!!.hashCode() else 0
        result = 31 * result + if (isMentsu) 1 else 0
        return result
    }

    companion object {

        /**
         * @param tile1 1枚目
         * @param tile2 2枚目
         * @return 2枚が一致すればtrue
         */
        fun check(tile1: Hai, tile2: Hai): Boolean {
            return tile1 sameAs tile2
        }

        /**
         * 対子になりうる牌をリストにして返す
         * @param tiles 手牌
         * @return 雀頭候補の対子リスト
         */
        @Throws(MahjongTileOverFlowException::class)
        fun findJantoCandidate(tiles: IntArray): List<Toitsu> {
            val result = ArrayList<Toitsu>(7)
            for (i in tiles.indices) {
                if (tiles[i] > 4) {
                    throw MahjongTileOverFlowException(i, tiles[i])
                }
                if (tiles[i] >= 2) {
                    result.add(Toitsu(Hai.factory.newInstance(i)))
                }
            }
            return result
        }
    }
}
