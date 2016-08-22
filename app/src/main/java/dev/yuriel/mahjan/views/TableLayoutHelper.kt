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

package dev.yuriel.mahjan.views

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import dev.yuriel.kotmvp.Dev
import dev.yuriel.mahjan.group.HandsGroup
import dev.yuriel.mahjan.group.LeftSideGroup
import dev.yuriel.mahjan.group.OpposideGroup
import dev.yuriel.mahjan.group.RightSideGroup

/**
 * Created by yuriel on 8/8/16.
 */
@Deprecated("")
class TableLayoutHelper {
    val layout: Table = Table()

    fun calculate(
            bottom: HandsGroup,
            left: LeftSideGroup,
            oppo: OpposideGroup,
            right: RightSideGroup) {
        layout.debug = Dev.VIR_DEBUG
        layout.setFillParent(true)
//        layout.width = Dev.getDefaultWidth()
//        layout.height = Dev.getDefaultHeight()
        layout.defaults().align(Align.center)

        layout.add(oppo).width(oppo.width).height(oppo.height)
        layout.row()
        layout.add(left).width(left.width).height(left.height).expandY()
        layout.add().expand()
        layout.add(right).width(right.width).height(right.height).expandY()
        layout.row()
        layout.add(bottom).width(bottom.width).height(bottom.height)
    }
}