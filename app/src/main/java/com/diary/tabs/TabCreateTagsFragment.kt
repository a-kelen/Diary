package com.diary.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.diary.R
import com.diary.createnote.CreateNoteVMFactory
import com.diary.createnote.CreateNoteViewModel
import com.diary.database.DiaryDatabase
import com.diary.databinding.FragmentTabCreateFolderBinding
import com.diary.databinding.FragmentTabCreateTagsBinding
import com.diary.home.ItemClickListerner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabCreateTagsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabCreateTagsFragment(val viewModel: CreateNoteViewModel) : Fragment(), ItemClickListerner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private lateinit var binding : FragmentTabCreateTagsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {binding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_tab_create_tags,container,false)

        binding.viewModel = viewModel
        viewModel.TagCretedEvent.observe(this.viewLifecycleOwner, Observer { isUpdated ->
            if(isUpdated) {
                viewModel.reloadFolderCreatedEvent()
                binding.tagName.setText("")
            }
        })
        binding.addTagBtn.setOnClickListener {
            if(! binding.tagName.text.isNullOrEmpty()) {
                viewModel.createTag()
            }
        }

        val adapter : TagAdapter = TagAdapter(this, viewModel)
        binding.checkBoxList.layoutManager = LinearLayoutManager(this.context)
        binding.checkBoxList.setHasFixedSize(true)
        binding.checkBoxList.adapter = adapter
        viewModel.tags.observe(this.viewLifecycleOwner, Observer { newList ->
            newList?.let {
                adapter.submitList(newList)
            }
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabCreateTagsFragment.
         */
        // TODO: Rename and change types and number of parameters

    }

    override fun OnClick(str: String) {
        TODO("Not yet implemented")
    }
}
