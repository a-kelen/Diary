package com.diary.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diary.notedetails.NoteAdapter
import com.diary.R
import com.diary.database.DiaryDatabase
import com.diary.database.Folder
import com.diary.databinding.FragmentHomeBinding
import com.diary.notedetails.NoteListerner
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), LifecycleObserver,
    ItemClickListerner {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        Timber.i("onCreate called")
    }
    lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.i("onCreateView called")
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,container,false)
        var application = requireNotNull(this.activity).application
        var dataSource = DiaryDatabase.getInstance(application)
        val viewModelFactory =
            HomeVMFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
        val adapter : NoteAdapter =
            NoteAdapter(NoteListerner {
                noteId -> viewModel.onNoteClicked(noteId)
            })

        binding.notes.layoutManager = LinearLayoutManager(this.context)
        binding.notes.setHasFixedSize(true)
        binding.notes.adapter = adapter
        binding.viewModel = viewModel

        viewModel.notes.observe(this.viewLifecycleOwner, Observer { newList ->
            newList?.let {
                adapter.submitList(newList)
            }
        })
        viewModel.event.observe(this.viewLifecycleOwner, Observer { isUpdated ->
            if(isUpdated) {
                viewModel.reloadEvent()
            }
        })
        viewModel.showSnackBarEvent.observe(this.viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "List is cleared",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })
        viewModel.navigateToNote.observe(this.viewLifecycleOwner, Observer {noteId ->
            noteId?.let {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNoteDetailsFragment(noteId))
                viewModel.onNoteNavigated()
            }
        })

        viewModel.folderlist.observe(viewLifecycleOwner, object: Observer<List<Folder>> {
            override fun onChanged(data: List<Folder>?) {
                data ?: return
                val chipGroup = binding.folderList
                val inflator = LayoutInflater.from(chipGroup.context)

                val children = data.map { folder ->
                    val chip = inflator.inflate(R.layout.folder_chip, chipGroup, false) as Chip
                    chip.text = folder.name
                    chip.tag = folder.name
                    chip.setOnCheckedChangeListener { button, isChecked ->
                        if (isChecked) {
                            adapter.submitList(viewModel.notes.value?.filter { x -> x.folderName == button.tag as String })
                        } else {
                            adapter.submitList(viewModel.notes.value)
                        }
                    }
                    chip
                }
                chipGroup.removeAllViews()
                for (chip in children) {
                    chipGroup.addView(chip)
                }
            }
        })
        binding.createBtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }


        binding.setLifecycleOwner(this)
        return  binding.root
    }

    override fun OnClick(str: String) {
        var b = bundleOf("element" to str)
        Navigation.findNavController(binding.notes).navigate(R.id.action_homeFragment_to_noteDetailsFragment, b)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Timber.i("onSaveInstanceState called with ")
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")

    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }
}
interface ItemClickListerner{
    fun OnClick(str: String)
}
