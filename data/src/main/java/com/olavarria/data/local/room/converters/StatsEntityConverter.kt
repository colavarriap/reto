package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.StatResponse

class StatsEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(statsEntities: List<StatResponse>) : String? {
        if (statsEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<StatResponse>>() {}.type
        return gson.toJson(statsEntities, type)
    }

    @TypeConverter
    fun to(statsEntitiesString: String?): List<StatResponse>? {
        if(statsEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<StatResponse>>() {}.type
        return gson.fromJson(statsEntitiesString, type)
    }
}