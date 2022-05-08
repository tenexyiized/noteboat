package com.example.notesboats.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.result.successOr
import com.example.notesboats.db.Notes
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.example.notesboats.usecases.FakeNotesRepository
import com.example.notesboats.usecases.GetAllNotesUseCase
import com.example.notesboats.usecases.NotesAddUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesAddViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var notesAddViewModel: NotesAddViewModel
    private lateinit var getAllNotesUseCase: GetAllNotesUseCase

    @Before
    fun setUp() {
        val fakeNotesRepository: FakeNotesRepository = FakeNotesRepository()
        notesAddViewModel = NotesAddViewModel(
            NotesAddUseCase(
                fakeNotesRepository,
                coroutineRule.testDispatcher
            )
        )

        getAllNotesUseCase = GetAllNotesUseCase(
            coroutineRule.testDispatcher,
            fakeNotesRepository
        )
    }

    @Test
    fun notesAddSuccess() = coroutineRule.runBlockingTest {
        val noteToBeInserted = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        notesAddViewModel.addNotes(noteToBeInserted)

        var result:com.example.common.result.Result<List<Notes>> = getAllNotesUseCase(Unit).first()

        var list = result.successOr(mutableListOf())

        Truth.assertThat(list).contains(noteToBeInserted)

    }

    @After
    fun tearDown() {

    }

}