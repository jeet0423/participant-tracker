package com.participanttracker.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.participanttracker.app.data.repository.ParticipantRepositoryImpl
import com.participanttracker.app.domain.repository.ParticipantRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindParticipantRepository(
        impl: ParticipantRepositoryImpl
    ): ParticipantRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}
