package com.example.puckodynamics.ui.blocks

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.puckodynamics.misc.BlockInterface
import java.util.*


abstract class BlockBase(context: Context?) : LinearLayout(context), BlockInterface {

    private val uuid: UUID = UUID.randomUUID()

    // Приконекченные блоки, лево,вверх,право,низ
    private val connectPins = mutableMapOf<String, UUID?>(RIGHT to null, TOP to null, LEFT to null, BOTTOM to null)

    protected var isBlockSelected: Boolean = false

    private lateinit var listener: OnTouchListener
    private lateinit var touchBtn: View

    protected fun setViewToTouchListener(view: View, onTouchListener: OnTouchListener) {
        touchBtn = view
        this.listener = onTouchListener
    }

    override fun delete() {
        (this.parent as ViewGroup).removeView(this)

    }

    fun getUuid(): UUID {
        return uuid
    }

    override fun deAttach() {
        connectPins.forEach { (s, _) ->
            connectPins[s] = null
        }
    }

    override fun getPins(): MutableMap<String, UUID?> {
        return connectPins
    }

    fun connectBlock(key: String, uuid: UUID) {
        connectPins[key] = uuid
    }

    fun getConnectBlock(key: String): UUID? {
        return connectPins[key]
    }

    override fun toggleSelect() {
        isBlockSelected = !isBlockSelected
        if (isBlockSelected)
            touchBtn.setOnTouchListener(listener)
        else
            touchBtn.setOnTouchListener(null)
    }

    companion object {
        const val RIGHT = "right"
        const val TOP = "top"
        const val LEFT = "left"
        const val BOTTOM = "BOTTOM"
    }

}