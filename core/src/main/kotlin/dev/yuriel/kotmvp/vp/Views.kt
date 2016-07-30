package dev.yuriel.kotmvp.vp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by yuriel on 7/18/16.
 */
interface Views {
    fun init(inflater: LayoutInflater, container: ViewGroup?)
    fun getView(): View
}

abstract class BaseViews: Views {

}