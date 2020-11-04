package com.diary

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.diary.databinding.FragmentHomeBinding
import com.diary.databinding.FragmentThirdBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentThirdBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third,container,false)

        binding.radio.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { gr, id ->
            val r1 = binding.radioButton
            val r2 = binding.radioButton2
            if(r1.id == id)
                binding.textView.text = r1.text
            if(r2.id == id)
                binding.textView.text = r2.text
        })
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.textView2.text = binding.editText.text
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                val arr = resources.getStringArray(R.array.spinnerItems)
                binding.textView3.text = arr[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.textView3.text = "0"
            }
        }
        binding.checkBox.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked)
                binding.textView4.text = "true"
            else
                binding.textView4.text = "false"
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textView5.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.share_menu, menu)
    }
    private fun getShareIntent() : Intent {
        val intent = Intent(Intent.ACTION_SEND)
        val str =  StringBuilder()
            .append("Results:")
            .append("\nTextview 1: ")
            .append(binding.textView.text)
            .append("\nTextview 2: ")
            .append(binding.textView2.text)
            .append("\nTextview 3: ")
            .append(binding.textView3.text)
            .append("\nTextview 4: ")
            .append(binding.textView4.text)
            .append("\nTextview 5: ")
            .append(binding.textView5.text)
            .toString()
        intent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, str)
        return  intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }


}
