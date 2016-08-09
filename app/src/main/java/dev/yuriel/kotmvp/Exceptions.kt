package dev.yuriel.kotmvp


/**
 * Created by yuriel on 8/5/16.
 */
open class GameException(message: String): Exception(message) {
    open val advice: String = ""
    override val message: String?
        get() = super.message + ". " + advice
}

class AlreadyDestroyedException: GameException("廃棄されたオブジェクトは二度と使わない")
class LayoutWrongException: GameException("レイアウトが間違えます")