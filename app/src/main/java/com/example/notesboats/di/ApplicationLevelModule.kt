package com.example.notesboats.di

import android.content.Context
import androidx.room.Room
import com.example.common.eventbus.CustomEventBus
import com.example.notesboats.db.NotesDao
import com.example.notesboats.db.NotesDatabase
import com.example.notesboats.repositories.NoteRepositoryImpl
import com.example.notesboats.repositories.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationLevelModule {

    @Singleton
    @Provides
    fun getEventBus() = CustomEventBus()

    @Singleton
    @Provides
    fun getNotesRepository(notesDao: NotesDao):NotesRepository {
        return NoteRepositoryImpl(notesDao)
    }

    @Singleton
    @Provides
    fun getNotesDao(@Named("real_db") notesDatabase: NotesDatabase) = notesDatabase.notesDao()

    @Provides
    @Singleton
    @Named("real_db")
    fun provideDatabase(@ApplicationContext appContext: Context): NotesDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            NotesDatabase::class.java,
            "note_database.db"
        ).build()
    }

}