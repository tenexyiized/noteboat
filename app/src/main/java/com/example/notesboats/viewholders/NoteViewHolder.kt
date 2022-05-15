package com.example.notesboats.viewholders

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesboats.R
import com.example.notesboats.adapters.NotesAdapter
import com.example.notesboats.db.Notes

class NoteViewHolder(
    viewGroup: ViewGroup,
    val noteInteractor: NotesAdapter.NoteInteractor,
    ): RecyclerView.ViewHolder(
    LayoutInflater
        .from(viewGroup.context)
        .inflate(R.layout.note_item, viewGroup, false)
    )
    {

        fun bind(notes: Notes){
            itemView.findViewById<TextView>(R.id.title_tv).text = notes.title
            itemView.findViewById<TextView>(R.id.description_tv).text = notes.description
            itemView.setOnClickListener {
                notes.id?.let{
                    noteInteractor.noteClicked(it)
                }
            }
        }

    }