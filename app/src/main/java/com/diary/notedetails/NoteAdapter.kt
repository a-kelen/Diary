package com.diary.notedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diary.domain.Note
import androidx.recyclerview.widget.ListAdapter
import com.diary.home.ItemClickListerner
import com.diary.databinding.NoteItemBinding

class NoteAdapter(
    val clickListerner: NoteListerner
) : ListAdapter<Note, NoteAdapter.NoteHolder>(
    NoteDiffCallback()
) {

    class NoteHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var title : TextView
        lateinit var content : TextView
        init {
            title = binding.noteItemTitle
            content = binding.noteItemContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val value : Note = getItem(position)
        holder.binding.note = value
        holder.binding.executePendingBindings()
        holder.binding.clickListener = clickListerner
//        holder.title.text = value.title
//        holder.content.text = value.content
//        holder.textView.setOnClickListener {
//            itemClickListerner.OnClick(value)
//        }
    }
}
class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }
}
class NoteListerner(val clickListerner: (noteId: Long) -> Unit) {
    fun onClick(note: Note) = clickListerner(note.noteId)
}
