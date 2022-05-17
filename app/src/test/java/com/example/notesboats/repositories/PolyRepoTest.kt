package com.example.notesboats.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesboats.network.apis.PolyApi
import com.example.notesboats.rules.main.MainCoroutineRule
import com.example.notesboats.rules.main.runBlockingTest
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PolyRepoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var SUT:PolyRepo

    @Before
    fun setUp() {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val api = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/jobinlawrance/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PolyApi::class.java)

        SUT = PolyRepo(api)
    }

    @Test
    fun getResponse() = runBlocking {
        val list = SUT.getDynamicList()
        System.out.println(list)
        Truth.assertThat(list).isNotNull()
    }

    }
