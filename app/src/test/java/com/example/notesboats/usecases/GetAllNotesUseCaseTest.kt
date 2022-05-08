package com.example.notesboats.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesboats.repositories.NotesRepository
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllNotesUseCaseTest {

    private lateinit var getAllNotesUseCase: GetAllNotesUseCase
    private lateinit var notesRepository:FakeNotesRepository

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    @Before
    fun setUp() {
        notesRepository = FakeNotesRepository()

        getAllNotesUseCase = GetAllNotesUseCase(
            coroutineRule.testDispatcher,
            notesRepository
        )
    }

    @Test
    fun successInFetchingList() = coroutineRule.runBlockingTest {
        getAllNotesUseCase(Unit).first()
        Truth.assertThat(notesRepository.isListFetched).isTrue()
    }

    @After
    fun tearDown() {
    }
}