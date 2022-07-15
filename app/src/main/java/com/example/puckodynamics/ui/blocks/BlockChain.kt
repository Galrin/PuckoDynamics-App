package com.example.puckodynamics.ui.blocks

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.puckodynamics.databinding.BlockChainBinding
import com.example.puckodynamics.utils.toVisibility

//@SuppressLint("ViewConstructor")
//class BlockChain(context: Context, rootView: ViewGroup) : BlockBase(context) {
//
//    lateinit var binding: BlockChainBinding
//    private val blockList: MutableList<BlockBase> = mutableListOf()
//
//    init {
//        binding = BlockChainBinding.inflate(LayoutInflater.from(context), rootView, false)
//        this.addView(binding.root)
//        binding.root.setOnClickListener {
//            this.toggleSelect()
//            binding.menuBlock.root.visibility = this.isBlockSelected.toVisibility()
//        }
//        this.setViewToTouchListener(binding.menuBlock.touchBlock, binding.root)
//    }
//
//    fun getBlocks(): MutableList<BlockBase> {
//        return blockList
//    }
//    fun addBlock(block: BlockBase){
//        blockList.add(block)
//        binding.chain.addView(block)
//    }
//}