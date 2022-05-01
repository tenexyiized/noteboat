package com.example.notesboats.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.notesboats.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb() = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        NotesDatabase::class.java
    ).allowMainThreadQueries().build()

    @Provides
    fun notesDao(database: NotesDatabase) = database.notesDao()



}