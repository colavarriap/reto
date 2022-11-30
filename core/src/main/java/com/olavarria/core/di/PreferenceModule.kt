package com.olavarria.core.di

import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.core.di.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun providePreference(preferences: BasePreferenceManager): Preferences
}