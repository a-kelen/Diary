package com.diary

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.diary.database.DiaryDatabase
import com.diary.databinding.CreateNoteFragmentBinding
import com.diary.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout

class CreateNoteFragment : Fragment() {

    companion object {
        fun newInstance() = CreateNoteFragment()
    }

    private lateinit var viewModel: CreateNoteViewModel
    private lateinit var binding : CreateNoteFragmentBinding
    private lateinit var animDown : ObjectAnimator
    private lateinit var tabCreate : LinearLayout
    private lateinit var animUp : ObjectAnimator
    private lateinit var tabAdapter : TabPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_note_fragment,container,false)
        var application = requireNotNull(this.activity).application
        var dataSource = DiaryDatabase.getInstance(application).noteDao
        val viewModelFactory = CreateNoteVMFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CreateNoteViewModel::class.java)
        tabCreate = binding.tabCreate
        val d = resources.getDimensionPixelSize(R.dimen.tabCreateDistanceDown)
        val d2 = resources.getDimensionPixelSize(R.dimen.tabCreateDistanceUp)
        animDown = ObjectAnimator.ofFloat(tabCreate,"translationY", d.toFloat()).apply {
            duration = 200
        }
        animUp = ObjectAnimator.ofFloat(tabCreate,"translationY", d2.toFloat()).apply {
            duration = 200
        }
        tabAdapter = TabPagerAdapter(requireActivity().supportFragmentManager)
        tabAdapter.addFragment(TabCreateSmileFragment(), "Smile")
        tabAdapter.addFragment(TabCreateFolderFragment(), "Folder")
        tabAdapter.addFragment(TabCreateTagsFragment(), "Tags")
        tabAdapter.addFragment(TabCreateImageFragment(), "Image")
        binding.viewPagerCreate.adapter = tabAdapter
        binding.tabLayoutCreate.setupWithViewPager(binding.viewPagerCreate)
        binding.tabLayoutCreate.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tabCreate.translationY == d.toFloat())
                    animUp.start()
                binding.viewPagerCreate.setCurrentItem(tab!!.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if(tabCreate.translationY == d.toFloat())
                    animUp.start()
                else
                    animDown.start()
            }
        })
        binding.viewModel = viewModel
        viewModel.NoteCretedEvent.observe(this.viewLifecycleOwner, Observer { isUpdated ->
            if(isUpdated) {
                viewModel.reloadNoteCreatedEvent()
                Navigation.findNavController(this.tabCreate).popBackStack()
            }
        })
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }
}
