package com.example.notesboats.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.helpers.OneShotEvent
import com.example.common.viewstates.NoteAddViewState
import com.example.notesboats.db.Notes
import com.example.notesboats.usecases.GetAllNotesUseCase
import com.example.notesboats.usecases.GetNoteByIdUseCase
import com.example.notesboats.usecases.NotesAddUseCase
import com.example.notesboats.usecases.NotesDeleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.common.result.Result

@HiltViewModel
class NotesAddViewModel
@Inject
constructor(
    val notesAddUseCase: NotesAddUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase
): ViewModel(){

   private var _uiState:MutableStateFlow<NoteAddViewState> = MutableStateFlow(NoteAddViewState())
   val uiState:StateFlow<NoteAddViewState> = _uiState

    fun addNotes(notes: Notes) {
        viewModelScope.launch {
            getViewState().id?.let{
                notes.id = it
            }
            var result = notesAddUseCase(notes)
            if(result is Result.Success){
                _uiState.value = getViewState().copy(
                    id = result.data
                )
            }
        }
    }

    fun getNotes(id:Long){
        viewModelScope.launch {
            var result = getNoteByIdUseCase(id)
            if(result is Result.Success){
                result.data?.let {
                    _uiState.value = getViewState().copy(
                        note = OneShotEvent(it)
                    )
                }
            }
        }
    }

    fun getViewState() = uiState.value


}