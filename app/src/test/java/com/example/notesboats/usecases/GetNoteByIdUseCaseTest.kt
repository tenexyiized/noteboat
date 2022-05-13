package com.example.notesboats.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetNoteByIdUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()


    private lateinit var SUT:GetNoteByIdUseCase
    private lateinit var fakeNotesRepository: FakeNotesRepository

    @Before
    fun setUp() {
        fakeNotesRepository = FakeNotesRepository()
        SUT = GetNoteByIdUseCase(
            fakeNotesRepository,
            coroutineRule.testDispatcher
        )
    }

    @Test
    fun getNoteById_IdPassedToNoteRepository() = coroutineRule.runBlockingTest {
        SUT(4)
        Truth.assertThat(fakeNotesRepository.idToBeRetreived).isEqualTo(4)
    }

    @After
    fun tearDown() {
    }
}