package com.example.notesboats.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.viewstates.NoteAddViewState
import com.example.notesboats.db.Notes
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.example.notesboats.usecases.*
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var SUT:NotesListViewModel
    private lateinit var noteAddUseCase:NotesAddUseCase

    @Before
    fun setUp() {
        val fakeNotesRepository = FakeNotesRepository()
        val getAllNotesUseCase = GetAllNotesUseCase(
            coroutineRule.testDispatcher,
            fakeNotesRepository
        )

        val notesDeleteUseCase = NotesDeleteUseCase(
            fakeNotesRepository,
            coroutineRule.testDispatcher
        )

         noteAddUseCase = NotesAddUseCase(
            fakeNotesRepository,
            coroutineRule.testDispatcher
        )

        SUT = NotesListViewModel(
            getAllNotesUseCase,
            notesDeleteUseCase
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun GetAllNotesHaveInsertedNotes() = coroutineRule.runBlockingTest {
        val notes: Notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        noteAddUseCase(notes)

        var list = SUT.uiState.first().notesList

        Truth.assertThat(list).contains(notes)

    }

    @Test
    fun deletedNoteNotPresentInDb() = coroutineRule.runBlockingTest {
        val notes: Notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        noteAddUseCase(notes)
        SUT.notesDeleteUseCase(notes)

        var list = SUT.uiState.first().notesList

        Truth.assertThat(list).doesNotContain(notes)

    }
}