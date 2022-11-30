package com.olavarria.data.repository

import com.olavarria.core.base.BaseRepository
import com.olavarria.core.common.ApiState
import com.olavarria.data.local.room.dao.EventsDao
import com.olavarria.data.local.room.entity.EventsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val eventsDao: EventsDao
) : LocalRepository, BaseRepository() {

    override fun insert(ticket: EventsEntity): Flow<ApiState<Unit>> {
        return onRoomCall { eventsDao.insert(ticket) }
    }

    override suspend fun insertObject(ticket: List<EventsEntity>) {
        eventsDao.insertObject(ticket)
    }

    override fun delete(): Flow<ApiState<Unit>> {
        return onRoomCall { eventsDao.delete() }
    }

    override fun getObjects(): Flow<ApiState<MutableList<EventsEntity>>> {
        return onRoomFlowCall(eventsDao.getAll())
    }

    override fun getEventsSearch(search: String): Flow<ApiState<MutableList<EventsEntity>>> {
        return onRoomFlowCall(eventsDao.getAllSearch(search))
    }

    override suspend fun update(ticket: EventsEntity) {
        TODO("Not yet implemented")
    }

}