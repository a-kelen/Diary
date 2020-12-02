package com.diary.tabs

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.diary.R
import com.diary.createnote.CreateNoteVMFactory
import com.diary.createnote.CreateNoteViewModel
import com.diary.database.DiaryDatabase
import com.diary.databinding.CreateNoteFragmentBinding
import com.diary.databinding.FragmentNoteDetailsBinding
import com.diary.databinding.FragmentTabCreateFolderBinding
import com.diary.home.ItemClickListerner
import com.diary.notedetails.NoteAdapter
import com.diary.notedetails.NoteDetailsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabCreateFolderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabCreateFolderFragment(val viewModel: CreateNoteViewModel) : Fragment(), ItemClickListerner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private lateinit var binding : FragmentTabCreateFolderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tab_create_folder,container,false)

        binding.viewModel = viewModel
        viewModel.FolderCretedEvent.observe(this.viewLifecycleOwner, Observer { isUpdated ->
            if(isUpdated) {
                viewModel.reloadFolderCreatedEvent()
                binding.folderName.setText("")
            }
        })
        binding.addFolderBtn.setOnClickListener {
            if(! binding.folderName.text.isNullOrEmpty()) {
                viewModel.createFolder()
                binding.folderName.setText("")
            }
        }

        val adapter : FolderAdapter = FolderAdapter(this)
        adapter.selectedId = viewModel.selectedFolder
        binding.radioList.layoutManager = LinearLayoutManager(this.context)
        binding.radioList.setHasFixedSize(true)
        binding.radioList.adapter = adapter
        viewModel.folders.observe(this.viewLifecycleOwner, Observer { newList ->
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
         * @return A new instance of fragment TabCreateFolderFragment.
         */
        // TODO: Rename and change types and number of parameters

    }

    override fun OnClick(str: String) {

    }
}
