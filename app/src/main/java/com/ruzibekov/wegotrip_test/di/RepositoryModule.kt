package com.ruzibekov.wegotrip_test.di

import com.ruzibekov.data.repository.AudioRepositoryImpl
import com.ruzibekov.data.repository.TourRepositoryImpl
import com.ruzibekov.domain.repository.AudioRepository
import com.ruzibekov.domain.repository.TourRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindTourRepository(repository: TourRepositoryImpl): TourRepository

    @Binds
    @Singleton
    fun bindAudioRepository(repository: AudioRepositoryImpl): AudioRepository
}