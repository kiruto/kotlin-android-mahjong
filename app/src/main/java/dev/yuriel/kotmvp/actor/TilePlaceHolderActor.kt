package dev.yuriel.kotmvp.actor

import dev.yuriel.kotmvp.interfaces.BaseActor

/**
 * Created by yuriel on 8/5/16.
 */
class TilePlaceHolderActor: BaseActor(){
    var position: Int = 0
        set(value) {
            if (value > -1 || value < 14) {
                field = value
            }
        }


}