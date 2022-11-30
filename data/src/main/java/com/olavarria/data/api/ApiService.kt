package com.olavarria.data.api

import com.olavarria.data.api.response.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("sport/events")
    suspend fun getSportEvents(
        @QueryMap queryMap: Map<String, String>,
    ): Response<ApiResponse<SportEventsResponse>>

}