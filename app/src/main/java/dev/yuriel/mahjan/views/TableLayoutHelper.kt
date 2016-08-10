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