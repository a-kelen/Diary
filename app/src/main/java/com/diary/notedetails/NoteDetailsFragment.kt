package com.diary.notedetails

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.diary.R
import com.diary.database.DiaryDatabase
import com.diary.databinding.FragmentNoteDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NoteDetailsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentNoteDetailsBinding
    private lateinit var viewModel: NoteDetailsViewModel
    private lateinit var viewModelFactory: NoteDetailsViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_note_details,container,false)
        var application = requireNotNull(this.activity).application
        var dataSource = DiaryDatabase.getInstance(application)
        var res = NoteDetailsFragmentArgs.fromBundle(requireArguments()).noteId
        viewModelFactory = NoteDetailsViewModelFactory(res, dataSource, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NoteDetailsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.note = viewModel.element
        return binding.root
    }
    fun buzz() {
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(CORRECT_BUZZ_PATERN, -1))
            } else {
                buzzer.vibrate(CORRECT_BUZZ_PATERN, -1)
            }
        }
    }
    companion object {
        val CORRECT_BUZZ_PATERN = longArrayOf(100,400,250,400,200,200,200,200,200,200)
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoteDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
