package com.example.notesboats.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NotesDatabase
    private lateinit var dao:NotesDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.notesDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertNotes() = runBlockingTest {
        val notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )

        dao.insertNotes(notes)

        var insertedNote = dao.getAllNotes().first()

        Truth.assertThat(insertedNote).contains(notes)

    }

    @Test
    fun deleteNotes() = runBlockingTest {
        val notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis()
        )

        dao.deleteNotes(notes)

        var insertedNote = dao.getAllNotes()

       // Truth.assertThat(insertedNote).is

    }
}