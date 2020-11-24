package com.diary.archive

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diary.notedetails.NoteAdapter
import com.diary.R
import com.diary.home.ItemClickListerner
import com.diary.database.DiaryDatabase
import com.diary.databinding.ArchiveFragmentBinding

class ArchiveFragment : Fragment(), ItemClickListerner {

    companion object {
        fun newInstance() = ArchiveFragment()
    }

    private lateinit var viewModel: ArchiveViewModel
    lateinit var binding : ArchiveFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.archive_fragment,container,false)
        var application = requireNotNull(this.activity).application
        var dataSource = DiaryDatabase.getInstance(application).noteDao
        val viewModelFactory =
            ArchiveVMFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArchiveViewModel::class.java)

        val adapter : NoteAdapter =
            NoteAdapter(this)

        binding.archiveNotes.layoutManager = LinearLayoutManager(this.context)
        binding.archiveNotes.setHasFixedSize(true)
        binding.archiveNotes.adapter = adapter
        viewModel.notes.observe(this.viewLifecycleOwner, Observer { newList ->
            newList?.let {
                adapter.submitList(newList )
            }
        })

        viewModel.status.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                Log.i("qwe", it)
            }
        })
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArchiveViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun OnClick(str: String) {

    }

}
