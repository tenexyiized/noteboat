package com.example.notesboats.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesboats.db.Notes
import com.example.notesboats.usecases.GetAllNotesUseCase
import com.example.notesboats.usecases.NotesAddUseCase
import com.example.notesboats.usecases.NotesDeleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesAddViewModel
@Inject
constructor(
    val notesAddUseCase: NotesAddUseCase
): ViewModel(){

    fun addNotes(notes: Notes) {
        viewModelScope.launch {
            var result = notesAddUseCase(notes)
        }
    }


}