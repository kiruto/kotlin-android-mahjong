/*
 * Copyright (C) 2016. Yuriel - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package dev.yuriel.kotmahjan.rules

import dev.yuriel.kotmahjan.models.*
import dev.yuriel.kotmahjan.rules.yaku.国士無双

import java.util.*

/**
 * Created by yuriel on 7/23/16.
 * 手牌に関する操作全般を扱います
 * このクラスのインスタンスをMahjongクラスに投げると
 * 点数が返ってくるようにしたいと考えています
 * TODO: ツモって牌を捨てるオペレーションメソッド
 */
class Hands {
    // -------------------------確定系-----------------------

    //確定した上がりの形のリスト
    private val mentsuCompSet = HashSet<MentsuSupport>()

    //確定した各牌の数一覧
    var handsComp = IntArray(34)

    //最後の牌
    var last: Hai? = null
        private set

    //あがれるか
    var canWin = false
        private set

    //食い下がりかどうか
    var isOpen = false
        private set

    // ------------------------ストック系----------------------

    // コンストラクタで入力された面子リスト
    private var inputtedMentsuList: MutableList<Mentsu> = mutableListOf()

    // 操作する用のストック
    private val handStocks = IntArray(34)

    // コンストラクタで入力された各牌の数の配列
    private var inputtedTiles: IntArray? = null
    var isKokushimuso = false
        private set

    /**
     * @param otherTiles
     * @param last
     * @param mentsuList
     * @throws MahjongTileOverFlowException
     */
    @Throws(MahjongTileOverFlowException::class, IllegalMentsuSizeException::class)
    constructor(otherTiles: IntArray, last: Hai, mentsuList: MutableList<Mentsu>) {
        inputtedTiles = otherTiles
        this.last = last
        inputtedMentsuList = mentsuList
        setHandsComp(otherTiles, mentsuList)
        findMentsu()
    }

    /**
     * @param otherTiles
     * @param last
     * @param mentsu
     * @throws MahjongTileOverFlowException
     */
    @Throws(MahjongTileOverFlowException::class, IllegalMentsuSizeException::class)
    constructor(otherTiles: IntArray, last: Hai, vararg mentsu: Mentsu) {
        inputtedTiles = otherTiles
        setHandsComp(otherTiles, Arrays.asList<Mentsu>(*mentsu))
        this.last = last
        Collections.addAll<Mentsu>(inputtedMentsuList, *mentsu)
        findMentsu()
    }

    /**
     * @param allTiles lastの牌も含めて下さい合計14になるはずです
     * @param last     この牌もotherTilesに含めてください
     */
    @Throws(HandsOverFlowException::class, MahjongTileOverFlowException::class, IllegalMentsuSizeException::class)
    constructor(allTiles: IntArray, last: Hai) {
        inputtedTiles = allTiles
        this.last = last

        //整合性をチェック
        checkTiles(allTiles)

        handsComp = allTiles

        findMentsu()
    }

    /**
     * コンストラクタで面子を入力した場合に
     * 面子を各牌の数に変換します
     * @param otherTiles 各牌の数
     * @param mentsuList 面子のリスト
     */
    private fun setHandsComp(otherTiles: IntArray, mentsuList: List<Mentsu>) {
        System.arraycopy(otherTiles, 0, handsComp, 0, otherTiles.size)
        for (mentsu in mentsuList) {
            val code: Int = mentsu.getHai()?.getCode() ?: continue

            if (mentsu.isOpen) {
                isOpen = true
            }

            if (mentsu is Shuntsu) {
                handsComp[code - 1] += 1
                handsComp[code] += 1
                handsComp[code + 1] += 1
            } else if (mentsu is Kotsu) {
                handsComp[code] += 3
            } else if (mentsu is Kantsu) {
                handsComp[code] += 4
            } else if (mentsu is Toitsu) {
                handsComp[code] += 2
            }
        }
    }

    fun getMentsuCompSet(): Set<MentsuSupport> {
        return mentsuCompSet
    }

    @Throws(HandsOverFlowException::class)
    private fun checkTiles(allTiles: IntArray) {
        var num = 0
        for (tileNum in allTiles) {
            num += tileNum
            if (num > 14) {
                throw HandsOverFlowException()
            }
        }
    }

    fun initStock() {
        System.arraycopy(inputtedTiles!!, 0, handStocks, 0, inputtedTiles!!.size)
    }

    /**
     * 槓子は見つけません
     */
    @Throws(MahjongTileOverFlowException::class, IllegalMentsuSizeException::class)
    fun findMentsu() {
        checkTileOverFlow()

        // 国士無双型の判定
        initStock()

        if (国士無双.isMatch(null, null, null, handStocks)) {
            isKokushimuso = true
            canWin = true
            return
        }

        // 雀頭の候補を探してストックしておく
        initStock()
        val toitsuList = Toitsu.findJantoCandidate(handStocks)

        // 雀頭が一つも見つからなければfalse
        if (toitsuList.size == 0) {
            canWin = false
            return
        }

        //七対子なら保存しておく
        if (toitsuList.size == 7) {
            canWin = true
            val mentsuList = ArrayList<Mentsu>(7)
            mentsuList.addAll(toitsuList)
            val comp = MentsuSupport(mentsuList, last)
            mentsuCompSet.add(comp)
        }

        // その他の判定
        //雀頭候補から探す
        val winCandidate = ArrayList<Mentsu>(4)
        for (toitsu in toitsuList) {
            // 操作変数を初期化
            init(winCandidate, toitsu)

            //刻子優先検索
            //検索
            winCandidate.addAll(findKotsuCandidate())
            winCandidate.addAll(findShuntsuCandidate())
            //全て0かチェック
            convertToMentsuComp(winCandidate)

            init(winCandidate, toitsu)
            //順子優先検索
            winCandidate.addAll(findShuntsuCandidate())
            winCandidate.addAll(findKotsuCandidate())
            convertToMentsuComp(winCandidate)
        }

    }

    /**
     * 同じ牌が5個以上はありえないので、Exception をthrow
     * @throws MahjongTileOverFlowException
     */
    @Throws(MahjongTileOverFlowException::class)
    private fun checkTileOverFlow() {
        //
        for (i in handsComp.indices) {
            val hand = handsComp[i]
            if (hand > 4) {
                canWin = false
                throw MahjongTileOverFlowException(i, hand)
            }
        }
    }

    /**
     * 操作変数・面子の候補を初期化し
     * 雀頭の分をストックから減らします
     * @param winCandidate 面子の候補
     * @param toitsu       この検索サイクルの雀頭候補
     */
    private fun init(winCandidate: MutableList<Mentsu>, toitsu: Toitsu) {
        // 操作変数を初期化
        initStock()
        winCandidate.clear()
        //ストックから雀頭を減らす
        handStocks[toitsu.getHai()?.getCode()?: -1] -= 2
        winCandidate.add(toitsu)
    }

    /**
     * handsStockが全て0の場合
     * winCandidateは完成しているので
     * mentsuCompに代入します
     * @param winCandidate mentsuCompに代入するかもしれない
     */
    @Throws(IllegalMentsuSizeException::class)
    private fun convertToMentsuComp(winCandidate: MutableList<Mentsu>) {
        //全て0かチェック
        if (isAllZero(handStocks)) {
            canWin = true
            winCandidate.addAll(inputtedMentsuList)
            val mentsuComp = MentsuSupport(winCandidate, last)
            if (!mentsuCompSet.contains(mentsuComp)) {
                mentsuCompSet.add(mentsuComp)
            }
        }
    }

    /**
     * 入力の配列が全て0かを調べます
     * @param stocks 調べたい配列
     * @return すべて0ならtrue ひとつでも0でなければfalse
     */
    private fun isAllZero(stocks: IntArray): Boolean {
        for (i in stocks) {
            if (i != 0) {
                return false
            }
        }
        return true
    }

    private fun findShuntsuCandidate(): List<Mentsu> {
        val resultList = ArrayList<Mentsu>(4)
        //字牌などはチェックしないので26まで
        for (j in 1..25) {
            // whileにしたのは一盃口などがあるから
            while (handStocks[j - 1] > 0 && handStocks[j] > 0 && handStocks[j + 1] > 0) {
                val shuntsu = Shuntsu(
                        false,
                        Hai.factory.newInstance(j - 1),
                        Hai.factory.newInstance(j),
                        Hai.factory.newInstance(j + 1))

                //3つ並んでいても順子であるとは限らないので調べる
                if (shuntsu.isMentsu) {
                    resultList.add(shuntsu)
                    handStocks[j - 1]--
                    handStocks[j]--
                    handStocks[j + 1]--
                }
            }
        }
        return resultList
    }

    private fun findKotsuCandidate(): List<Mentsu> {
        val resultList = ArrayList<Mentsu>(4)
        for (i in handStocks.indices) {
            if (handStocks[i] >= 3) {
                resultList.add(Kotsu(false, Hai.factory.newInstance(i)))
                handStocks[i] -= 3
            }
        }
        return resultList
    }
}
