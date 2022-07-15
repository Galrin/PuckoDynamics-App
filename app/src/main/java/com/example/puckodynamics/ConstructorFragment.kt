package com.example.puckodynamics

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.puckodynamics.databinding.FragmentConstructorBinding
import com.example.puckodynamics.ui.blocks.*
import com.example.puckodynamics.ui.blocks.BlockBase.Companion.BOTTOM
import com.example.puckodynamics.ui.blocks.BlockBase.Companion.LEFT
import com.example.puckodynamics.ui.blocks.BlockBase.Companion.RIGHT
import com.example.puckodynamics.ui.blocks.BlockBase.Companion.TOP
import com.example.puckodynamics.utils.TouchListener
import com.example.puckodynamics.utils.getConnectBlocks
import com.example.puckodynamics.utils.hasTouchTwoView
import com.example.puckodynamics.utils.toVisibility


class ConstructorFragment : Fragment() {

    private lateinit var binding: FragmentConstructorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = FragmentConstructorBinding.inflate(inflater, container, false)

        BlockSingleton.blockList.forEach {
            (it.parent as ViewGroup).removeView(it)
            binding.container.addView(it)
        }

        binding.intro.visibility = BlockSingleton.blockList.isEmpty().toVisibility()

        return binding.root
    }

    // Текущий блок выбран дял перемещения
    private fun hasBlockMoved(hasTouch: Boolean) {
        binding.deleteContainer.visibility = hasTouch.toVisibility()
        BlockSingleton.blockList.forEach {
            it.toggleZoneConnect(hasTouch)
        }
    }

    private fun hasTouchBlockWithDelete(blockX: Int, blockY: Int, hasTouch: Boolean, block: BlockBase) {
        if (hasTouchTwoView(binding.deleteContainer, blockX, blockY)) {
            binding.deleteContainer.setBackgroundColor(resources.getColor(R.color.red_translucent))
            if (!hasTouch) {
                block.delete()
            }
        } else
            binding.deleteContainer.setBackgroundColor(resources.getColor(R.color.gray_translucent))

        BlockSingleton.blockList.forEach { blockWithPins ->
            if (block == blockWithPins)
                println("TODO")
            else {
                blockWithPins.getFreePins().forEach { pin ->

                    if (hasTouchTwoView(pin.value, blockX, blockY)) {
                        pin.value.setBackgroundColor(resources.getColor(R.color.green_translucent))
                        if (!hasTouch) {
                            println("Connect")
                            val paramsBlockWithPins = block.getParams()
                            when (pin.key) {
                                RIGHT -> {
                                    blockWithPins.connectBlock(RIGHT, block.getUuid())
                                    block.connectBlock(LEFT, blockWithPins.getUuid())
                                    block.translationX = blockWithPins.x + paramsBlockWithPins.width
                                    block.translationY = blockWithPins.y
                                }
                                LEFT -> {
                                    blockWithPins.connectBlock(LEFT, block.getUuid())
                                    block.connectBlock(RIGHT, blockWithPins.getUuid())
                                    block.translationX = blockWithPins.x - paramsBlockWithPins.width
                                    block.translationY = blockWithPins.y
                                }
                                TOP -> {
                                    blockWithPins.connectBlock(TOP, block.getUuid())
                                    block.connectBlock(BOTTOM, blockWithPins.getUuid())
                                    block.translationX = blockWithPins.x
                                    block.translationY = blockWithPins.y - paramsBlockWithPins.height
                                }
                                BOTTOM -> {
                                    blockWithPins.connectBlock(BOTTOM, block.getUuid())
                                    block.connectBlock(TOP, blockWithPins.getUuid())
                                    block.translationX = blockWithPins.x
                                    block.translationY = blockWithPins.y + paramsBlockWithPins.height
                                }
                            }
                        }
                    } else
                        pin.value.setBackgroundColor(resources.getColor(R.color.light_green_translucent))
                }
            }
        }
    }

    private var counter = 1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.intro.setOnClickListener {
            BlockSingleton.blockList.forEach {
                println(it.getPins())
            }
        }



        binding.fab.setOnClickListener { fab ->
            val popup = PopupMenu(requireContext(), fab)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_block, popup.menu)
            popup.setOnMenuItemClickListener { item: MenuItem ->

                when (item.itemId) {
                    R.id.block_start -> {
                        val customBlock = BlockStart(requireContext(), binding.root)
                        binding.root.addView(customBlock)
                        BlockSingleton.blockList.add(customBlock)
                        binding.intro.visibility = (BlockSingleton.blockList.size == 0).toVisibility()
                        val touchListener = TouchListener()
                        customBlock.setOnClickListener {
                            touchListener.toggleZoneDelete { hasBlockMoved(it) }
                            touchListener.hasCrossBlock { blockX, blockY, hasTouch, block -> hasTouchBlockWithDelete(blockX = blockX, blockY = blockY, hasTouch = hasTouch, block = block) }
                            customBlock.setTouchListener(touchListener.getOnTouchListener(binding.container, getConnectBlocks(customBlock, BlockSingleton.blockList)))
                            customBlock.toggleSelect()
                        }
                        customBlock.setName("First name $counter")
                        customBlock.setDescription("First description $counter")
                        counter++
                        true
                    }
                    R.id.block_await_if -> {
                        val customBlock = BlockAwaitIf(requireContext(), binding.root)
                        binding.root.addView(customBlock)
                        BlockSingleton.blockList.add(customBlock)
                        binding.intro.visibility = (BlockSingleton.blockList.size == 0).toVisibility()
                        val touchListener = TouchListener()
                        customBlock.setOnClickListener {
                            touchListener.toggleZoneDelete { hasBlockMoved(it) }
                            touchListener.hasCrossBlock { blockX, blockY, hasTouch, block -> hasTouchBlockWithDelete(blockX = blockX, blockY = blockY, hasTouch = hasTouch, block = block) }
                            customBlock.setTouchListener(touchListener.getOnTouchListener(binding.container, getConnectBlocks(customBlock, BlockSingleton.blockList)))
                            customBlock.toggleSelect()
                        }
                        customBlock.setName("First name $counter")
                        customBlock.setDescription("First description $counter")
                        counter++
                        true
                    }
                    R.id.block_action -> {
                        val customBlock = BlockAction(requireContext(), binding.root)
                        binding.root.addView(customBlock)
                        BlockSingleton.blockList.add(customBlock)
                        binding.intro.visibility = (BlockSingleton.blockList.size == 0).toVisibility()
                        val touchListener = TouchListener()
                        customBlock.setOnClickListener {
                            touchListener.toggleZoneDelete { hasBlockMoved(it) }
                            touchListener.hasCrossBlock { blockX, blockY, hasTouch, block -> hasTouchBlockWithDelete(blockX = blockX, blockY = blockY, hasTouch = hasTouch, block = block) }
                            customBlock.setTouchListener(touchListener.getOnTouchListener(binding.container, getConnectBlocks(customBlock, BlockSingleton.blockList)))
                            customBlock.toggleSelect()
                        }
                        customBlock.setName("First name $counter")
                        customBlock.setDescription("First description $counter")
                        counter++
                        true
                    }
                    R.id.block_now_if -> {
                        val customBlock = BlockNowIf(requireContext(), binding.root)
                        binding.root.addView(customBlock)
                        binding.intro.visibility = (BlockSingleton.blockList.size == 0).toVisibility()
                        BlockSingleton.blockList.add(customBlock)
                        val touchListener = TouchListener()
                        customBlock.setOnClickListener {
                            touchListener.toggleZoneDelete { hasBlockMoved(it) }
                            touchListener.hasCrossBlock { blockX, blockY, hasTouch, block -> hasTouchBlockWithDelete(blockX = blockX, blockY = blockY, hasTouch = hasTouch, block = block) }
                            customBlock.setTouchListener(touchListener.getOnTouchListener(binding.container, getConnectBlocks(customBlock, BlockSingleton.blockList)))
                            customBlock.toggleSelect()
                        }
                        customBlock.setName("First name $counter")
                        customBlock.setDescription("First description $counter")
                        counter++
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

    }
}