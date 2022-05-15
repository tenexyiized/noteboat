package com.example.notesboats.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesboats.db.Notes
import com.example.notesboats.viewholders.NoteViewHolder

class NotesAdapter(val noteInteractor: NoteInteractor) : ListAdapter<Notes, RecyclerView.ViewHolder>(BlockDiff){

    interface NoteInteractor{
        fun noteDeleted(id:Long)
        fun noteClicked(id:Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder(parent,noteInteractor)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NoteViewHolder).bind(getItem(position) )
    }

    override fun submitList(list: List<Notes>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    object BlockDiff : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes) = oldItem == newItem

    }


}