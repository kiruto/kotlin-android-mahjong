/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel<yuriel3183@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.yuriel.kotmahjan.rules

/**
 * Created by yuriel on 7/24/16.
 */
enum class Score constructor(val ron: Int, val parentTsumo: Int, val parent: Int, val child: Int) {
    SCORE0(0, 0, 0, 0),
    SCORE1000(1000, 0, 500, 300),
    SCORE1300(1300, 0, 700, 400),
    SCORE1500(1500, 500, 0, 0),
    SCORE1600(1600, 0, 800, 400),
    SCORE2000(2000, 700, 1000, 500),
    SCORE2300(2300, 0, 1200, 600),
    SCORE2400(2400, 800, 0, 0),
    SCORE2600(2600, 0, 1300, 700),
    SCORE2900(2900, 1000, 1500, 800),
    SCORE3200(3200, 0, 1600, 800),
    SCORE3400(3400, 1200, 0, 0),
    SCORE3600(3600, 0, 1800, 900),
    SCORE3900(3900, 1300, 2000, 1000),
    SCORE4400(4400, 1500, 0, 0),
    SCORE4500(4500, 0, 2300, 1200),
    SCORE4800(4800, 1600, 0, 0),
    SCORE5200(5200, 0, 2600, 1300),
    SCORE5300(5300, 1800, 0, 0),
    SCORE5800(5800, 2000, 2900, 1500),
    SCORE6400(6400, 0, 3200, 1600),
    SCORE6800(6800, 2300, 0, 0),
    SCORE7100(7100, 0, 3600, 1800),
    SCORE7700(7700, 2600, 3900, 2000),
    SCORE8000(8000, 0, 4000, 2000),
    SCORE8700(8700, 2900, 0, 0),
    SCORE9600(9600, 3200, 0, 0),
    SCORE10600(10600, 3600, 0, 0),
    SCORE11600(11600, 3900, 0, 0),
    SCORE12000(12000, 4000, 6000, 3000),
    SCORE16000(16000, 0, 8000, 4000),
    SCORE18000(18000, 6000, 0, 0),
    SCORE24000(24000, 8000, 12000, 6000),
    SCORE32000(32000, 0, 16000, 8000),
    SCORE36000(36000, 12000, 0, 0),
    SCORE48000(48000, 16000, 0, 0);


    companion object {

        fun calculateYakumanScore(isParent: Boolean, yakumanSize: Int): Score {
            when (yakumanSize) {
                1 -> return if (isParent) SCORE48000 else SCORE32000
            }// TODO: ダブル役満, トリプル役満, etc...
            return SCORE0
        }

        fun calculateScore(isParent: Boolean, han: Int, fu: Int): Score {
            if (han >= 13) {
                return if (isParent) SCORE48000 else SCORE32000
            }
            when (han) {
                12, 11 -> return if (isParent) SCORE36000 else SCORE24000
                10, 9, 8 -> return if (isParent) SCORE24000 else SCORE16000
                7, 6 -> return if (isParent) SCORE18000 else SCORE12000
                5 -> return if (isParent) SCORE12000 else SCORE8000
                4 -> return calc4Han(isParent, fu)
                3 -> return calc3Han(isParent, fu)
                2 -> return calc2Han(isParent, fu)
                1 -> return calc1Han(isParent, fu)
            }
            return SCORE0
        }

        private fun calc4Han(isParent: Boolean, fu: Int): Score {
            if (fu == 25) return if (isParent) SCORE9600 else SCORE6400
            if (fu > 30) return if (isParent) SCORE12000 else SCORE8000
            if (fu > 20) return if (isParent) SCORE11600 else SCORE7700
            return if (isParent) SCORE7700 else SCORE5200
        }

        private fun calc3Han(isParent: Boolean, fu: Int): Score {
            if (fu == 25) return if (isParent) SCORE4800 else SCORE3200
            if (fu > 60) return if (isParent) SCORE12000 else SCORE8000
            if (fu > 50) return if (isParent) SCORE11600 else SCORE7700
            if (fu > 40) return if (isParent) SCORE9600 else SCORE6400
            if (fu > 30) return if (isParent) SCORE7700 else SCORE5200
            if (fu > 20) return if (isParent) SCORE5800 else SCORE3900
            return if (isParent) SCORE3900 else SCORE2600
        }

        private fun calc2Han(isParent: Boolean, fu: Int): Score {
            if (fu == 25) return if (isParent) SCORE2400 else SCORE1600
            if (fu > 100) return if (isParent) SCORE10600 else SCORE7100
            if (fu > 90) return if (isParent) SCORE9600 else SCORE6400
            if (fu > 80) return if (isParent) SCORE8700 else SCORE5800
            if (fu > 70) return if (isParent) SCORE7700 else SCORE5200
            if (fu > 60) return if (isParent) SCORE6800 else SCORE4500
            if (fu > 50) return if (isParent) SCORE5800 else SCORE3900
            if (fu > 40) return if (isParent) SCORE4800 else SCORE3200
            if (fu > 30) return if (isParent) SCORE3900 else SCORE2600
            if (fu > 20) return if (isParent) SCORE2900 else SCORE2000
            return if (isParent) SCORE2000 else SCORE1300
        }

        private fun calc1Han(isParent: Boolean, fu: Int): Score {
            if (fu > 100) return if (isParent) SCORE5300 else SCORE3600
            if (fu > 90) return if (isParent) SCORE4800 else SCORE3200
            if (fu > 80) return if (isParent) SCORE4400 else SCORE2900
            if (fu > 70) return if (isParent) SCORE3900 else SCORE2600
            if (fu > 60) return if (isParent) SCORE3400 else SCORE2300
            if (fu > 50) return if (isParent) SCORE2900 else SCORE2000
            if (fu > 40) return if (isParent) SCORE2400 else SCORE1600
            if (fu > 30) return if (isParent) SCORE2000 else SCORE1300
            return if (isParent) SCORE1500 else SCORE1000
        }
    }
}
