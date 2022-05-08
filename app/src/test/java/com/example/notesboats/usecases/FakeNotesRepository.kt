package com.example.notesboats.usecases

import com.example.notesboats.db.Notes
import com.example.notesboats.repositories.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNotesRepository : NotesRepository{
    var list = mutableListOf<Notes>()

    var isListFetched  = false

    override suspend fun insertNotes(notes: Notes) {
        list.add(notes)
    }

    override suspend fun deleteNotes(notes: Notes) {
        list.remove(notes)
    }

    override fun getAllNotes(): Flow<List<Notes>> = flow {
        isListFetched = true
        emit(list)
    }

}
