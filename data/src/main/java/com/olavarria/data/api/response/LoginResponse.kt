package com.olavarria.data.api.response

import com.squareup.moshi.Json

data class LoginResponse (
    @Json(name = "accessToken")
    val token: String,
    @Json(name = "expiresIn")
    val expired: String,
    @Json(name = "tokenType")
    val tokenType: String,
    @Json(name = "user")
    val user: UserResponse
)