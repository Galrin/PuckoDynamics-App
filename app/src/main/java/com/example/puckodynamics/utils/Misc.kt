package com.example.puckodynamics.utils

import android.graphics.Rect
import android.view.View
import com.example.puckodynamics.ui.blocks.BlockBase


fun hasTouchTwoView(viewOne: View, viewTwoX: Int, viewTwoY: Int): Boolean {
    val outRect = Rect()
    val location = IntArray(2)
    viewOne.getDrawingRect(outRect)
    viewOne.getLocationOnScreen(location)
    outRect.offset(location[0], location[1])
    return outRect.contains(viewTwoX, viewTwoY)
}

fun getConnectBlocks(blockBase: BlockBase, blockList: List<BlockBase>): List<BlockBase> {
    if (blockList.isEmpty())
        return emptyList()
    // Создаем итоговый лист Сет и пихаем в него исходный блок
    val resultList = mutableSetOf<BlockBase>()
    // Создаем лист и пихаем в него уид первого блока
    // Пока в этом листе есть элементы будем искать блоки
    val tempList = mutableSetOf(blockBase.getUuid())
    // Создаем лист и пихаем в него уид первого блока
    // Храним в этом листе все использованные уиды
    val tempList2 = mutableSetOf(blockBase.getUuid())

    // Ищем блоки, пока лист новых уидов не опустеет
    while (tempList.size > 0) {
        // Берем первый попавшийся ууид из листа
        val uuid = tempList.first()
        // Находим первый(единственный) попавшийся блок с таким уидом
        val block = blockList.first { it.getUuid() == uuid }
        // Достаем все пины этого блока
        block.getPins().forEach { (_, tempUuid) ->
            // Проверяем что у пина есть коннект, достаем уид
            if (tempUuid != null) {
                // Проверяем что такой уид еще не использовали
                if (tempList2.all { it != tempUuid }) {
                    // Если новый уид добавляем его в оба листа
                    tempList.add(tempUuid)
                    tempList2.add(tempUuid)
                }
            }
        }
        // удаляем уид, по которому только что прошлись
        tempList.remove(uuid)
        // Добавляем блок уида, по которому только что прошлись
        resultList.add(block)
    }

    println("resultList.size ${resultList.size}")
    return resultList.toList()
}