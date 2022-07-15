package com.example.puckodynamics.utils

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.puckodynamics.ui.blocks.BlockBase
import java.lang.Math.*

class TouchListener {

    lateinit var hasTouchBlock: (hasTouch: Boolean) -> Unit
    fun toggleZoneDelete(onTouch: (hasTouch: Boolean) -> Unit) {
        this.hasTouchBlock = onTouch
    }

    lateinit var hasTouchBlockWithDelete: (blockX: Int, blockY: Int, hasTouch: Boolean, block: BlockBase) -> Unit
    fun hasCrossBlock(onTouch: (blockX: Int, blockY: Int, hasTouch: Boolean, block: BlockBase) -> Unit) {
        this.hasTouchBlockWithDelete = onTouch
    }

    fun getOnTouchListenerMove(container: ViewGroup, blockList: List<BlockBase>): View.OnTouchListener {

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
                        it.setSelectedBlock(false)
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

    fun getOnTouchListenerScale(blockList: List<BlockBase>): View.OnTouchListener {

        val MAX_ZOOM = 1f
        val MIN_ZOOM = 0.9f

        var xDelta = 0f
        var yDelta = 0f

        val onTouchListener = View.OnTouchListener { view, event ->
            view.performClick()

            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    xDelta = event.rawX
                    yDelta = event.rawY
                }
                MotionEvent.ACTION_UP -> {
                    blockList.forEach {
                        it.setSelectedBlock(false)
                    }
                }
                MotionEvent.ACTION_POINTER_DOWN -> {}
                MotionEvent.ACTION_POINTER_UP -> {}
                MotionEvent.ACTION_MOVE -> {
                    val x = (xDelta - event.rawX) * 2 + (yDelta - event.rawY) * 2
                    val d = if (x < 0) 1f else kotlin.math.sqrt(x)
                    val scale = kotlin.math.max(MIN_ZOOM, kotlin.math.min(d, MAX_ZOOM))
                    blockList.forEach {
                        println(d)

                        it.scaleX *= scale
                        it.scaleY *= scale

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