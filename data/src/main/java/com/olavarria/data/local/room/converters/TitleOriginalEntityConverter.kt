package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.MatchDayResponse
import com.olavarria.data.api.response.TitleOriginalResponse

class TitleOriginalEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(titleOriginalEntities: TitleOriginalResponse?): String? {
        if (titleOriginalEntities==null) return null

        val type = object : TypeToken<TitleOriginalResponse>() {}.type
        return gson.toJson(titleOriginalEntities, type)
    }

    @TypeConverter
    fun to(titleOriginalEntitiesString: String?): TitleOriginalResponse? {
        if (titleOriginalEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<TitleOriginalResponse>() {}.type
        return gson.fromJson(titleOriginalEntitiesString, type)
    }

}