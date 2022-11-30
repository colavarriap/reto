package com.olavarria.data.di

import android.content.Context
import androidx.room.Room
import com.olavarria.data.local.room.AppDatabase
import com.olavarria.data.local.room.dao.EventsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "crp.db"
        ).build()
    }

    @Provides
    fun provideObjectDao(appDatabase: AppDatabase): EventsDao {
        return appDatabase.EventsDao()
    }

}