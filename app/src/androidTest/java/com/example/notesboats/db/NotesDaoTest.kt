package com.example.notesboats.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.notesboats.rules.MainCoroutineRule
import com.example.notesboats.rules.runBlockingTest
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class NotesDaoTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Inject
    @Named("test_db")
    lateinit var database: NotesDatabase
    private lateinit var dao:NotesDao

    @Before
    fun setUp(){
        hiltAndroidRule.inject()
        dao = database.notesDao()
        var site = "interviewbit"
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertNotesIntoDb_DbContaintsItem() = coroutineRule.runBlockingTest {
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
    fun deleteNotesAfterInsertion_dbEmpty() = coroutineRule.runBlockingTest {
        val noteToBeDeleted = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )

        dao.insertNotes(noteToBeDeleted)
        dao.deleteNotes(noteToBeDeleted)

        var insertedNote = dao.getAllNotes().first()

        Truth.assertThat(insertedNote).isEmpty()

    }

    @Test
    fun notesEditedAndInserted_DbHasUpdatedValue() = coroutineRule.runBlockingTest {
        val noteToBeInserted = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )

        dao.insertNotes(noteToBeInserted)

        val editedNote =  noteToBeInserted.copy(
            title = "jello"
        )

        dao.insertNotes(editedNote)

        var noteList = dao.getAllNotes().first()

        Truth.assertThat(editedNote).isEqualTo(noteList.get(0))
        Truth.assertThat(noteList).hasSize(1)


    }

}