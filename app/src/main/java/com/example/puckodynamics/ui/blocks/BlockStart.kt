package com.example.puckodynamics.ui.blocks

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.puckodynamics.R
import com.example.puckodynamics.data.model.Params
import com.example.puckodynamics.databinding.BlockStartBinding
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

        binding.menuBlock.settingBlock.setOnClickListener {
            openMenu()
        }

    }

    override fun setTouchListenerMove(onTouchListener: OnTouchListener) {
        this.setViewToTouchListenerMove(binding.menuBlock.touchBlock, onTouchListener)
    }

    override fun setTouchListenerScale(onTouchListener: OnTouchListener) {
        this.setViewToTouchListenerScale(binding.menuBlock.scaleBlock, onTouchListener)
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

    override fun setName(name: String) {
        binding.name.text = name
    }

    override fun setDescription(description: String) {
        binding.description.text = description
    }

    override fun toggleSelect() {
        super.toggleSelect()
        binding.menuBlock.root.visibility = this.isBlockSelected.toVisibility()
    }

    override fun openMenu() {
        val args = Bundle()
        args.putString("UUID", getUuid().toString())
        args.putString("typeBlock", this::class.simpleName)
        findNavController().navigate(R.id.action_constructorFragment_to_blockSettingFragment, args)
    }
}