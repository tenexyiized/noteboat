package com.example.notesboats.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesboats.db.Notes
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import com.example.common.result.Result
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesAddUseCaseTest {

    private lateinit var notesAddUseCase:NotesAddUseCase

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        var fakeNotesRepository:FakeNotesRepository = FakeNotesRepository()
        notesAddUseCase = NotesAddUseCase(
            fakeNotesRepository,
            coroutineRule.testDispatcher
        )
    }

    @Test
    fun addNotesSuccess() = coroutineRule.runBlockingTest {
        val notes:Notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        Truth.assertThat(
            notesAddUseCase(notes)
        ).isEqualTo(Result.Success(true))

    }

    @After
    fun tearDown() {
    }
}