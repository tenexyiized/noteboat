package com.example.notesboats.viewmodels

import androidx.lifecycle.ViewModel
import com.example.notesboats.usecases.GetAllNotesUseCase
import com.example.notesboats.usecases.NotesDeleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel
@Inject
constructor(
    getAllNotesUseCase: GetAllNotesUseCase,
    notesDeleteUseCase: NotesDeleteUseCase
): ViewModel(){

}