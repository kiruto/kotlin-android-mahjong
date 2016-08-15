/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 yuriel
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
 *
 */

package dev.yuriel.kotmvp.vp

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by yuriel on 7/18/16.
 */
abstract class PresenterActivity<out VIEWS: Views>: AppCompatActivity() {
    protected val views: VIEWS by lazy { getViewsClass().newInstance() }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        try {
            views.init(layoutInflater, null)
            setContentView(views.getView())
            onBindViews()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        onDestroyViews()
        super.onDestroy()
    }

    protected abstract fun getViewsClass(): Class<out VIEWS>
    protected abstract fun onBindViews()
    protected abstract fun onDestroyViews()
}

abstract class PresenterFragment<out VIEWS: Views>: Fragment() {
    protected val views: VIEWS by lazy { getViewsClass().newInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            views.init(inflater, container)
            onBindViews()
            return views.getView()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onDestroyView() {
        onDestroyViews()
        super.onDestroyView()
    }

    protected abstract fun getViewsClass(): Class<out VIEWS>
    protected abstract fun onBindViews()
    protected abstract fun onDestroyViews()
}

abstract class PresenterAdapter<VIEWS: Views>: BaseAdapter() {
    protected var views: VIEWS? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        if (convertView == null) {
            val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            try {
                views = getViewsClass().newInstance()
                views?.init(inflater, parent)
                view = views?.getView()
                view?.tag = views
            } catch (e: Exception) {
                return null
            }
        } else {
            view = convertView
            views = convertView.tag as VIEWS
        }
        if (convertView != null) {
            onBindItemViews(position)
        }
        return view
    }

    protected abstract fun getViewsClass(): Class<out VIEWS>
    protected abstract fun onBindItemViews(position: Int)
}