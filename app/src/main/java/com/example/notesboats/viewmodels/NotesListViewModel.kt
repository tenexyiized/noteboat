package com.example.notesboats.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.viewstates.NoteAddViewState
import com.example.common.viewstates.NoteListViewState
import com.example.notesboats.usecases.GetAllNotesUseCase
import com.example.notesboats.usecases.NotesDeleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
 import com.example.common.result.Result
import com.example.notesboats.db.Notes
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@HiltViewModel
class NotesListViewModel
@Inject
constructor(
    val getAllNotesUseCase: GetAllNotesUseCase,
    val notesDeleteUseCase: NotesDeleteUseCase
): ViewModel(){

    val uiState: StateFlow<NoteListViewState> = getAllNotesUseCase(Unit).map {
        if(it is Result.Success){
            Log.d("cnrd","hella " + it.data.size)
            val x = getViewState().copy(
                notesList = it.data
            )
            x
        }
        else{
            getViewState()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = NoteListViewState())

    fun deleteNotes(notes: Notes) = viewModelScope.launch {
        notesDeleteUseCase(notes)
    }

    private fun getViewState() = uiState.value


}