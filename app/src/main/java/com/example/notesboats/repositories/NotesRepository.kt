package com.example.notesboats.repositories

import com.example.notesboats.db.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertNotes(notes: Notes)
    suspend fun deleteNotes(notes: Notes)
    fun getAllNotes(): Flow<List<Notes>>

}