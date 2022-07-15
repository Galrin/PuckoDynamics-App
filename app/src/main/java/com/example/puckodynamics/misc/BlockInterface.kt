package com.example.puckodynamics.misc

import android.view.View
import android.widget.TextView
import com.example.puckodynamics.data.model.Params
import java.util.*

interface BlockInterface {

    fun openMenu()
    fun delete()
    fun toggleSelect()

    fun toggleZoneConnect(isShow: Boolean)

    fun setTouchListenerMove(onTouchListener: View.OnTouchListener)

    fun setTouchListenerScale(onTouchListener: View.OnTouchListener)

    fun deAttach()

    fun getFreePins(): MutableMap<String, TextView>

    fun getPins(): MutableMap<String, UUID?>

    fun getParams(): Params

    fun setParams(params: Params)

    fun setName(name: String)
    fun setDescription(description: String)
}