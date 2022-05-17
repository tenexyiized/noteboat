package com.example.notesboats.di

import android.content.Context
import androidx.room.Room
import com.example.common.eventbus.CustomEventBus
import com.example.notesboats.db.NotesDao
import com.example.notesboats.db.NotesDatabase
import com.example.notesboats.network.apis.PolyApi
import com.example.notesboats.repositories.NoteRepositoryImpl
import com.example.notesboats.repositories.NotesRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

    @Provides
    @Singleton
    fun getMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofitService(moshi: Moshi):Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/jobinlawrance/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providePolyApi(retrofit: Retrofit): PolyApi {
        return retrofit.create(PolyApi::class.java)
    }

}