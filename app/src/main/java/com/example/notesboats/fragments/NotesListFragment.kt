package com.example.notesboats.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.helpers.launchAndRepeatWithViewLifecycle
import com.example.common.navigation.ScreenNavigator
import com.example.notesboats.R
import com.example.notesboats.adapters.NotesAdapter
import com.example.notesboats.db.Notes
import com.example.notesboats.viewmodels.NotesListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotesListFragment : Fragment(R.layout.fragment_notes_list), NotesAdapter.NoteInteractor {

    val viewModel: NotesListViewModel by viewModels()
    protected val notesAdapter: NotesAdapter by lazy { NotesAdapter(this) }
    private lateinit var rvNotes:RecyclerView
    private lateinit var fab:FloatingActionButton

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv(view)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            noteClicked(null)
        }
        startCollecting()
    }

    private fun startCollecting() {
        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.uiState.collect{
                    notesAdapter.submitList(it.notesList)
                }
            }
        }
    }

    private fun setUpRv(view: View) {
        rvNotes = view.findViewById(R.id.noteList_rv)
        rvNotes.apply {
            adapter = notesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun noteDeleted(notes: Notes) {
        viewModel.deleteNotes(notes)
    }

    override fun noteClicked(id: Long?) {
        screenNavigator.openAddNotesPage(id)
    }

}