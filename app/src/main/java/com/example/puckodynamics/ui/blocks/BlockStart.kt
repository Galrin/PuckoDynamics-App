package com.example.puckodynamics.ui.blocks

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.puckodynamics.databinding.BlockStartBinding
import com.example.puckodynamics.models.Params
import com.example.puckodynamics.utils.toVisibility


@SuppressLint("ViewConstructor", "ClickableViewAccessibility")
class BlockStart(context: Context, rootView: ViewGroup) : BlockBase(context) {

    private var binding: BlockStartBinding

    init {
        binding = BlockStartBinding.inflate(LayoutInflater.from(context), rootView, false)
        this.addView(binding.root)

        binding.menuBlock.unpinBlock.setOnClickListener {
            this.deAttach()
        }

    }

    override fun setTouchListener(onTouchListener: OnTouchListener) {
        this.setViewToTouchListener(binding.menuBlock.touchBlock, onTouchListener)
    }

    override fun toggleZoneConnect(isShow: Boolean) {
        getFreePins().forEach { (_, view) ->
            view.visibility = isShow.toVisibility()
        }
    }

    override fun getParams(): Params {
        val location = IntArray(2)
        binding.root.getLocationOnScreen(location)
        return Params(width = binding.container.width, height = binding.container.height, x = location[0], y = location[1])
    }

    override fun setParams(params: Params) {
        binding.root.x = params.x.toFloat()
        binding.root.y = params.y.toFloat()
    }

    override fun getFreePins(): MutableMap<String, TextView> {
        val pinList: MutableMap<String, TextView> = mutableMapOf()
        pinList[BOTTOM] = binding.bottomPin.pin
        return pinList
    }

    fun setName(name: String) {
        binding.name.text = name
    }

    fun setDescription(description: String) {
        binding.description.text = description
    }

    override fun toggleSelect() {
        super.toggleSelect()
        binding.menuBlock.root.visibility = this.isBlockSelected.toVisibility()
    }
}