package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.TeamResponse

class TeamEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(teamEntities: TeamResponse?): String? {
        if (teamEntities==null) return null

        val type = object : TypeToken<TeamResponse>() {}.type
        return gson.toJson(teamEntities, type)
    }

    @TypeConverter
    fun to(teamEntitiesString: String?): TeamResponse? {
        if (teamEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<TeamResponse>() {}.type
        return gson.fromJson(teamEntitiesString, type)
    }

}