package com.olavarria.data.repository

import com.google.gson.Gson
import com.olavarria.core.base.BaseRepository
import com.olavarria.core.common.ApiState
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.data.api.ApiConstants.AUTHORIZATION_TOKEN
import com.olavarria.data.api.ApiService
import com.olavarria.data.api.ApiServiceToken
import com.olavarria.data.api.request.DeviceRequest
import com.olavarria.data.api.response.*
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiServiceToken: ApiServiceToken,
    private val preferenceManager: BasePreferenceManager
    ) : ApiRepository, BaseRepository() {

    override suspend fun validateLogin(request: DeviceRequest): ApiState<ApiResponse<LoginResponse>> {
        val body = Gson().toJson(request).toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        return onApiCallCoroutine { apiServiceToken.validateSignIn("application/json", AUTHORIZATION_TOKEN, body) }
    }

    override fun validateLoginFlow(request: DeviceRequest): Flow<ApiState<ApiResponse<LoginResponse>>> {
        val body = Gson().toJson(request).toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        return onApiCall { apiServiceToken.validateSignInFlow("application/json", AUTHORIZATION_TOKEN, body) }
    }

    override suspend fun getSportEvents(filters: Map<String, String>): ApiState<ApiResponse<SportEventsResponse>> {
        return onApiCallCoroutine { apiService.getSportEvents(filters) }
    }

}