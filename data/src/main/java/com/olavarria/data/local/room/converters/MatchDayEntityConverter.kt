package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.MatchDayResponse

class MatchDayEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(matchDayEntities: MatchDayResponse?): String? {
        if (matchDayEntities==null) return null

        val type = object : TypeToken<MatchDayResponse>() {}.type
        return gson.toJson(matchDayEntities, type)
    }

    @TypeConverter
    fun to(matchDayEntitiesString: String?): MatchDayResponse? {
        if (matchDayEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<MatchDayResponse>() {}.type
        return gson.fromJson(matchDayEntitiesString, type)
    }

}