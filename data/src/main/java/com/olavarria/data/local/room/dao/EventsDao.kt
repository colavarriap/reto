package com.olavarria.data.local.room.dao

import androidx.room.*
import com.olavarria.data.local.room.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cases: EventsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertObject(cases: List<EventsEntity>)

    @Query("DELETE FROM EventsEntity")
    suspend fun delete()

    @Query("SELECT * FROM EventsEntity")
    fun getAll(): Flow<MutableList<EventsEntity>>

    @Query("SELECT * FROM EventsEntity WHERE matchDayName like '%' || :search || '%'")
    fun getAllSearch(search: String): Flow<MutableList<EventsEntity>>

}