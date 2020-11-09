package com.diary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diary.database.Note

class NoteAdapter(val itemClickListerner: ItemClickListerner) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    var data= listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var textView : TextView
        init {
            textView = itemView.findViewById(R.id.noteItemTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        var item : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return  NoteHolder(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val value : Note = data.get(position)
        holder.textView.text = value.Title
//        holder.textView.setOnClickListener {
//            itemClickListerner.OnClick(value)
//        }
    }
}

//class NoteListener(val clickListener: (noteId: Long) -> Unit) {
//    fun onClick(note: String) = clickListener()
//}
