package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.EventStatusResponse

class EventStatusEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(eventStatusEntities: EventStatusResponse?): String? {
        if (eventStatusEntities==null) return null

        val type = object : TypeToken<EventStatusResponse>() {}.type
        return gson.toJson(eventStatusEntities, type)
    }

    @TypeConverter
    fun to(eventStatusEntitiesString: String?): EventStatusResponse? {
        if (eventStatusEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<EventStatusResponse>() {}.type
        return gson.fromJson(eventStatusEntitiesString, type)
    }

}