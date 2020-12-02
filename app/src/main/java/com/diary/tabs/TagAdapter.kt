package com.diary.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diary.createnote.CreateNoteViewModel
import com.diary.database.Folder
import com.diary.database.Tag
import com.diary.databinding.FolderItemBinding
import com.diary.databinding.NoteItemBinding
import com.diary.databinding.TagItemBinding
import com.diary.domain.Note
import com.diary.home.ItemClickListerner

class TagAdapter(
    val itemClickListerner: ItemClickListerner,
    val viewModel: CreateNoteViewModel
) : ListAdapter<Tag, TagAdapter.TagHolder>(
    TagDiffCallback()
) {

    init {
        viewModel.arr.value = ArrayList()
    }
    class TagHolder(val binding: TagItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TagItemBinding.inflate(layoutInflater, parent, false)
        return TagHolder(binding)
    }

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        val value : Tag = getItem(position)
        holder.binding.tag = value
        holder.binding.tagCheckBoxText.setOnClickListener {
            var cb = it as CheckBox
            if (cb.isChecked) {
                var tag = it.getTag().toString().toLong()
                viewModel.arr.value!!.add(tag)
            } else {
                var tag = it.getTag().toString().toLong()
                viewModel.arr.value!!.remove(tag)
            }
        }
    }
}
class TagDiffCallback : DiffUtil.ItemCallback<Tag>() {
    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.tagId == newItem.tagId
    }
}
