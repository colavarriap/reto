package com.olavarria.data.di

import com.olavarria.data.repository.LocalRepository
import com.olavarria.data.repository.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    @Singleton
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}