package com.example.notesboats.viewmodels

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
            getViewState().apply {
                notesList = it.data
            }
        }
        else{
            getViewState()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = NoteListViewState())

    fun deleteNotes(notes: Notes) = viewModelScope.launch {
        notesDeleteUseCase(notes)
    }

    fun getViewState() = uiState.value


}