package dev.yuriel.mahjan

import dev.yuriel.kotmahjan.ctrl.HaiMgr

/**
 * Created by yuriel on 8/7/16.
 */
class MockData4Test private constructor() {
    val haiList4Test: HaiMgr = HaiMgr()
    fun getTehaiList4Test() = haiList4Test.getTehai4Test()
    fun getHaiList4Test(count: Int) = haiList4Test.getHaiList4Test(count)

    companion object {
        val instance: MockData4Test by lazy { MockData4Test() }
        val instance2: MockData4Test by lazy { MockData4Test() }
        val instance3: MockData4Test by lazy { MockData4Test() }
        val instance4: MockData4Test by lazy { MockData4Test() }
    }
}