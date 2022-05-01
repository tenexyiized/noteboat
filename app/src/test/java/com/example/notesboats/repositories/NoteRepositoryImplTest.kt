package com.example.notesboats.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesboats.db.Notes
import com.example.notesboats.db.NotesDao
import com.example.notesboats.rules.MainCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteRepositoryImplTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var notesRepositoryImpl: NoteRepositoryImpl

    @Before
    fun setUp() {
        notesRepositoryImpl = NoteRepositoryImpl(NotesDaoTd())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun NotesInserted_Success() = coroutineRule.testDispatcher.runBlockingTest {
        val notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        notesRepositoryImpl.insertNotes(notes)
        Truth.assertThat( notesRepositoryImpl.getAllNotes().first() ).contains(notes)

    }

        class NotesDaoTd : NotesDao{

            var list = mutableListOf<Notes>()

            override suspend fun insertNotes(notes: Notes) {
                list.add(notes)
            }

            override suspend fun deleteNotes(notes: Notes) {
                list.remove(notes)
            }

            override fun getAllNotes(): Flow<List<Notes>> = flow {
                emit(list)
            }
        }
    }
