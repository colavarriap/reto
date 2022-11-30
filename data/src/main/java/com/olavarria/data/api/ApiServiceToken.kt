package com.olavarria.data.api

import com.olavarria.data.api.response.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceToken {

    @POST("auth/users/login/anonymous")
    suspend fun validateSignIn(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String,
        @Body params: RequestBody
    ): Response<ApiResponse<LoginResponse>>

    @POST("auth/users/login/anonymous")
    suspend fun validateSignInFlow(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String,
        @Body params: RequestBody
    ): ApiResponse<LoginResponse>
}