package com.olavarria.data.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.olavarria.data.api.response.ChildrenResponse

class ChildrenEntityConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(childrenEntities: ChildrenResponse?): String? {
        if (childrenEntities==null) return null

        val type = object : TypeToken<ChildrenResponse>() {}.type
        return gson.toJson(childrenEntities, type)
    }

    @TypeConverter
    fun to(childrenEntitiesString: String?): ChildrenResponse? {
        if (childrenEntitiesString.isNullOrEmpty()) return null

        val type = object : TypeToken<ChildrenResponse>() {}.type
        return gson.fromJson(childrenEntitiesString, type)
    }

}