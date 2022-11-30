package com.olavarria.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olavarria.data.local.room.converters.*
import com.olavarria.data.local.room.dao.EventsDao
import com.olavarria.data.local.room.entity.EventsEntity

@Database(entities = [EventsEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    MatchDayEntityConverter::class,
    BelongEntityConverter::class,
    ChildrenEntityConverter::class,
    EventStatusEntityConverter::class,
    StatsEntityConverter::class,
    TeamEntityConverter::class,
    TitleOriginalEntityConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun EventsDao(): EventsDao

}