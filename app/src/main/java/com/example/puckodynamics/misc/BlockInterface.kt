package com.example.puckodynamics.misc

import android.view.View
import android.widget.TextView
import com.example.puckodynamics.models.Params
import com.example.puckodynamics.ui.blocks.BlockBase
import java.util.*

interface BlockInterface {

    fun openMenu()
    fun delete()
    fun toggleSelect()

//    // Показать/скрыть зону для удаления блока
//    fun toggleZoneDelete(onTouch: (hasTouch: Boolean) -> Unit)

    // Показать/Скрыть зоны присоединения блоков к текущему блоку
    fun toggleZoneConnect(isShow: Boolean)
//
//    // Пересекает ли выбранный блок другой блок
//    fun hasCrossBlock(onTouch: (blockX: Int, blockY: Int, hasTouch: Boolean, block: BlockBase) -> Unit)

    fun setTouchListener(onTouchListener: View.OnTouchListener)

    fun deAttach()

    fun getFreePins() : MutableMap<String, TextView>

    fun getPins() : MutableMap<String, UUID?>

    fun getParams() : Params

    fun setParams(params: Params)
}