package com.diary.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.diary.R
import com.diary.createnote.CreateNoteVMFactory
import com.diary.createnote.CreateNoteViewModel
import com.diary.database.DiaryDatabase
import com.diary.databinding.FragmentTabCreateImageBinding
import com.diary.databinding.FragmentTabCreateSmileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabCreateSmileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabCreateSmileFragment(val viewModel: CreateNoteViewModel) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding : FragmentTabCreateSmileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tab_create_smile,container,false)

        binding.viewModel = viewModel
        binding.em1.setOnClickListener {
            viewModel.emotion.value = 1
            alert(1)
        }
        binding.em2.setOnClickListener {
            viewModel.emotion.value = 2
            alert(2)
        }
        binding.em3.setOnClickListener {
            viewModel.emotion.value = 3
            alert(3)
        }
        binding.em4.setOnClickListener {
            viewModel.emotion.value = 4
            alert(4)
        }
        binding.em5.setOnClickListener {
            viewModel.emotion.value = 5
            alert(5)
        }

        return binding.root
    }

    fun alert(i: Int){
        when(i) {
            1 -> {
                binding.em1.contentDescription = getString(R.string.selected_emoji)
                binding.em2.contentDescription = getString(R.string.emoji_button)
                binding.em3.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.emoji_button)
            }
            2 -> {
                binding.em1.contentDescription = getString(R.string.emoji_button)
                binding.em2.contentDescription = getString(R.string.selected_emoji)
                binding.em3.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.emoji_button)
            }
            3 -> {
                binding.em1.contentDescription = getString(R.string.emoji_button)
                binding.em2.contentDescription = getString(R.string.emoji_button)
                binding.em3.contentDescription = getString(R.string.selected_emoji)
                binding.em4.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.emoji_button)
            }
            4 -> {
                binding.em1.contentDescription = getString(R.string.emoji_button)
                binding.em2.contentDescription = getString(R.string.emoji_button)
                binding.em3.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.selected_emoji)
                binding.em4.contentDescription = getString(R.string.emoji_button)
            }
            5 -> {
                binding.em1.contentDescription = getString(R.string.emoji_button)
                binding.em2.contentDescription = getString(R.string.emoji_button)
                binding.em3.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.emoji_button)
                binding.em4.contentDescription = getString(R.string.selected_emoji)
            }
        }
    }
    companion object {

    }
}
