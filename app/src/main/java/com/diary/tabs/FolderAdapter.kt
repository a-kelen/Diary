package com.diary.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diary.database.Folder
import com.diary.databinding.FolderItemBinding
import com.diary.databinding.NoteItemBinding
import com.diary.domain.Note
import com.diary.home.ItemClickListerner

class FolderAdapter(
    val itemClickListerner: ItemClickListerner
) : ListAdapter<Folder, FolderAdapter.FolderHolder>(
    FolderDiffCallback()
) {
    lateinit var selectedId : MutableLiveData<Long>

    class FolderHolder(val binding: FolderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
//            name = binding.folderRadioText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FolderItemBinding.inflate(layoutInflater, parent, false)
        return FolderHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderHolder, position: Int) {
        val value : Folder = getItem(position)
        holder.binding.folder = value
        holder.binding.folderRadioText.isChecked = value.folderId == selectedId.value
        holder.binding.executePendingBindings()
        holder.binding.folderRadioText.setOnClickListener {
            selectedId.value = it.getTag().toString().toLong()
            notifyDataSetChanged()
        }
    }
}
class FolderDiffCallback : DiffUtil.ItemCallback<Folder>() {
    override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
        return oldItem.folderId == newItem.folderId
    }
}
