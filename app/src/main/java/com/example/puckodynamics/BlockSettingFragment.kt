package com.example.puckodynamics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.puckodynamics.databinding.FragmentBlockSettingBinding
import com.example.puckodynamics.ui.blocks.BlockAction
import com.example.puckodynamics.ui.blocks.BlockAwaitIf
import com.example.puckodynamics.ui.blocks.BlockNowIf
import com.example.puckodynamics.utils.toVisibility
import java.util.*


class BlockSettingFragment : Fragment() {

    private lateinit var binding: FragmentBlockSettingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentBlockSettingBinding.inflate(inflater, container, false)
        activity?.window?.attributes?.windowAnimations = R.style.DialogAnimation

        val uuid = UUID.fromString(arguments?.getString("UUID"))

        when (arguments?.getString("typeBlock")) {
            BlockAction::class.simpleName -> {
                binding.task.visibility = true.toVisibility()
            }
            BlockNowIf::class.simpleName -> {
                binding.ask.visibility = true.toVisibility()
            }
            BlockAwaitIf::class.simpleName -> {
                binding.ask.visibility = true.toVisibility()
                binding.time.visibility = true.toVisibility()
            }
            else -> {}
        }

        binding.saveBtn.setOnClickListener {
            val block = BlockSingleton.blockList.first { it.getUuid() == uuid }

            block.setName(binding.name.text.toString())
            block.setDescription(binding.description.text.toString())
        }

        return binding.root
    }
}