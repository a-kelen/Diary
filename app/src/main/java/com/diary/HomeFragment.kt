package com.diary

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.diary.database.DiaryDatabase
import com.diary.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), LifecycleObserver, ItemClickListerner {

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        var application = requireNotNull(this.activity).application
        var dataSource = DiaryDatabase.getInstance(application).noteDao
        val viewModelFactory = HomeVMFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
        val adapter : NoteAdapter = NoteAdapter(this)

        binding.notes.layoutManager = LinearLayoutManager(this.context)
        binding.notes.setHasFixedSize(true)
        binding.notes.adapter = adapter
        binding.viewModel = viewModel

        viewModel.notes.observe(this.viewLifecycleOwner, Observer { newList ->
            newList?.let {
                adapter.data = newList
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
        binding.setLifecycleOwner(this)
        return  binding.root
    }

    override fun OnClick(str: String) {
        Toast.makeText(this.activity, "Data : " + str, Toast.LENGTH_SHORT).show()
        var b = bundleOf("element" to str)
        Navigation.findNavController(binding.notes).navigate(R.id.action_homeFragment_to_noteDetailsFragment, b)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState!= null) {
            Timber.i("onActivityCreated :" + savedInstanceState.getString(ARG_PARAM1))
        }
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
