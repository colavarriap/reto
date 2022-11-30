package com.olavarria.data.repository

import com.olavarria.core.common.ApiState
import com.olavarria.data.api.request.DeviceRequest
import com.olavarria.data.api.response.*
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun validateLogin(request: DeviceRequest): ApiState<ApiResponse<LoginResponse>>

    fun validateLoginFlow(request: DeviceRequest): Flow<ApiState<ApiResponse<LoginResponse>>>

    suspend fun getSportEvents(filters: Map<String, String>): ApiState<ApiResponse<SportEventsResponse>>
}