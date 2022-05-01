package com.example.notesboats.repositories

import com.example.notesboats.db.Notes
import com.example.notesboats.db.NotesDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(val notesDao: NotesDao) : NotesRepository{

    override suspend fun insertNotes(notes: Notes) {
        notesDao.insertNotes(notes)
    }

    override suspend fun deleteNotes(notes: Notes) {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(): Flow<List<Notes>> = notesDao.getAllNotes()

}