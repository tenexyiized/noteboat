package com.example.notesboats.repositories

import com.example.notesboats.db.Notes
import com.example.notesboats.db.NotesDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(val notesDao: NotesDao) : NotesRepository{

    override suspend fun insertNotes(notes: Notes):Long {
        return notesDao.insertNotes(notes)
    }

    override suspend fun deleteNotes(notes: Notes) {
        notesDao.deleteNotes(notes)
    }

    override fun getAllNotes(): Flow<List<Notes>> = notesDao.getAllNotes()

    override suspend fun getNote(id: Long): Notes? = notesDao.getNote(id)

}