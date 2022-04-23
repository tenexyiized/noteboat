package com.example.notesboats.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("select * from notes order by lastUpdated")
    fun getAllNotes(): Flow<List<Notes>>


}