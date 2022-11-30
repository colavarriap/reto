package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.BelongResponse

class BelongEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(belongEntities: BelongResponse?): String? {
        if (belongEntities==null) return null

        val type = object : TypeToken<BelongResponse>() {}.type
        return gson.toJson(belongEntities, type)
    }

    @TypeConverter
    fun to(belongEntitiesString: String?): BelongResponse? {
        if (belongEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<BelongResponse>() {}.type
        return gson.fromJson(belongEntitiesString, type)
    }

}