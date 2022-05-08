package com.example.notesboats.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.result.Result
import com.example.notesboats.db.Notes
import com.example.notesboats.repositories.NotesRepository
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesDeleteUseCaseTest {

    private lateinit var notesDeleteUseCase: NotesDeleteUseCase

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        val notesRepository:NotesRepository = FakeNotesRepository()
        notesDeleteUseCase = NotesDeleteUseCase(
            notesRepository,
            coroutineRule.testDispatcher
        )
    }

    @Test
    fun successfullyDeleted() = coroutineRule.runBlockingTest {
        val notes: Notes = Notes(
            "hello",
            "cello",
            System.currentTimeMillis(),
            4
        )
        Truth.assertThat(
            notesDeleteUseCase(notes)
        ).isEqualTo(Result.Success(true))
    }

    @After
    fun tearDown() {
    }
}