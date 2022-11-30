package com.olavarria.data.repository

import com.olavarria.core.common.ApiState
import com.olavarria.data.local.room.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun insert(ticket: EventsEntity): Flow<ApiState<Unit>>

    suspend fun insertObject(ticket: List<EventsEntity>)

    fun delete(): Flow<ApiState<Unit>>

    fun getObjects(): Flow<ApiState<MutableList<EventsEntity>>>

    fun getEventsSearch(search: String): Flow<ApiState<MutableList<EventsEntity>>>

    suspend fun update(ticket: EventsEntity)

}