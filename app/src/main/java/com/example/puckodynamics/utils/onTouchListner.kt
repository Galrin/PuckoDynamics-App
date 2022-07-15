package com.example.puckodynamics.utils

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.puckodynamics.ui.blocks.BlockBase

class TouchListener {

    lateinit var hasTouchBlock: (hasTouch: Boolean) -> Unit
    fun toggleZoneDelete(onTouch: (hasTouch: Boolean) -> Unit) {
        this.hasTouchBlock = onTouch
    }

    lateinit var hasTouchBlockWithDelete: (blockX: Int, blockY: Int, hasTouch: Boolean, block: BlockBase) -> Unit
    fun hasCrossBlock(onTouch: (blockX: Int, blockY: Int, hasTouch: Boolean, block: BlockBase) -> Unit) {
        this.hasTouchBlockWithDelete = onTouch
    }

    fun getOnTouchListener(container: ViewGroup, blockList: List<BlockBase>): View.OnTouchListener {

        var xDelta = 0f
        var yDelta = 0f

        val onTouchListener = View.OnTouchListener { view, event ->
            view.performClick()

            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    xDelta = event.rawX
                    yDelta = event.rawY
                    hasTouchBlock(true)
                }
                MotionEvent.ACTION_UP -> {
                    hasTouchBlock(false)
                    blockList.forEach {
                        it.toggleSelect()
                        hasTouchBlockWithDelete(event.rawX.toInt(), event.rawY.toInt(), false, it)
                    }
                }
                MotionEvent.ACTION_POINTER_DOWN -> {}
                MotionEvent.ACTION_POINTER_UP -> {}
                MotionEvent.ACTION_MOVE -> {
                    val rootMinX = 0
                    val rootMinY = 0
                    val rootMaxX = container.width

                    blockList.forEach {
                        it.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                        val width = it.measuredWidth
                        it.translationX += (event.rawX - xDelta)
                        it.translationY += (event.rawY - yDelta)
                        if (it.translationX < rootMinX)
                            it.translationX = rootMinX.toFloat()
                        if (it.translationY < rootMinY)
                            it.translationY = rootMinY.toFloat()
                        if (it.translationX + width > rootMaxX)
                            it.translationX = rootMaxX.toFloat() - width

//                        println("rootMinX = $rootMinX, rootMinY = $rootMinY, rootMaxX = $rootMaxX")

                        hasTouchBlockWithDelete(event.rawX.toInt(), event.rawY.toInt(), true, it)
                    }

                    xDelta = event.rawX
                    yDelta = event.rawY
                }
            }
            true
        }

        return onTouchListener
    }

}