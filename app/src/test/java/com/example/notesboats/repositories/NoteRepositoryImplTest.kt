package com.example.notesboats.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesboats.db.Notes
import com.example.notesboats.db.NotesDao
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
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
    fun anbc(){
        Truth.assertThat( mutableListOf<Notes>() ).isEmpty()
    }

    @Test
    fun NotesInserted_Success() = coroutineRule.runBlockingTest {
        val notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        notesRepositoryImpl.insertNotes(notes)
        Truth.assertThat( notesRepositoryImpl.getAllNotes().first() ).contains(notes)

    }

    @Test
    fun notesDeletedSuccess() = coroutineRule.runBlockingTest {
        val note = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        notesRepositoryImpl.insertNotes(note)
        notesRepositoryImpl.deleteNotes(note)
        Truth.assertThat(notesRepositoryImpl.getAllNotes().first()).doesNotContain(note)

    }

    @Test
    fun NotesRetrievedById_NotePresent_NoteReturned() = coroutineRule.runBlockingTest {
        val note = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        notesRepositoryImpl.insertNotes(note)

       val noteRetrieved = notesRepositoryImpl.getNote(note.id!!)
        Truth.assertThat(note).isEqualTo(noteRetrieved)

    }

    @Test
    fun NotesRetrievedById_NoteNotPresent_NullReturned() = coroutineRule.runBlockingTest {
        val noteRetrieved = notesRepositoryImpl.getNote(5)
        Truth.assertThat(noteRetrieved).isEqualTo(null)

    }

        class NotesDaoTd : NotesDao{

            var list = mutableListOf<Notes>()

            override suspend fun insertNotes(notes: Notes):Long {
                list.add(notes)
                return notes.id!!
            }

            override suspend fun deleteNotes(notes: Notes) {
                list.remove(notes)
            }

            override fun getAllNotes(): Flow<List<Notes>> = flow {
                emit(list)
            }

            override suspend fun getNote(id: Long): Notes? {
                return list.find {
                    it.id == id
                }
            }
        }
    }
