package com.olavarria.data.api.response

import com.squareup.moshi.Json

data class ApiResponse<T> (
    @Json(name = "data")
    val body: T
)