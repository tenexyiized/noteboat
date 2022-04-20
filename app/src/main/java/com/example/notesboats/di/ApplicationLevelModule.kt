package com.example.notesboats.di

import com.example.common.eventbus.CustomEventBus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationLevelModule {

    @Singleton
    @Provides
    fun getEventBus() = CustomEventBus()

}