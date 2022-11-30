package com.olavarria.data.api.interceptor

import com.google.gson.Gson
import com.olavarria.core.di.preferences.Preferences
import com.olavarria.data.BuildConfig
import com.olavarria.data.api.ApiConstants
import com.olavarria.data.api.ApiConstants.AUTHORIZATION
import com.olavarria.data.api.ApiConstants.UNAUTHORIZED
import com.olavarria.data.api.ApiService
import com.olavarria.data.api.ApiServiceToken
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class AuthInterceptor @Inject constructor(
    @Named("OKHTTP") val okHttpClient: OkHttpClient,
    val preferences: Preferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val mainRequest = chain.request()
        val token = preferences.getToken()

        var builder: Request.Builder = mainRequest.newBuilder()
            .header(
                name = AUTHORIZATION,
                value = "Bearer $token"
            )
            .method(
                method = mainRequest.method,
                body = mainRequest.body
            )
        var response: Response = chain.proceed(builder.build())

        if (response.code == UNAUTHORIZED) {
            response.close()
            //chain.proceed(getTokenBuilder(mainRequest))
            preferences.setSession(false)
        }
        return response
    }

    private fun getTokenBuilder(request: Request): Request = runBlocking {

        val body = Gson().toJson(request).toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val tokenCall = accessTokenApi().validateSignIn("application/json", ApiConstants.AUTHORIZATION_TOKEN, body)

        var builder: Request.Builder = request.newBuilder()
            .header(AUTHORIZATION, "Bearer ${tokenCall.body()?.body?.token}")
            .method(request.method, request.body)
        builder.build()

    }

    private fun accessTokenApi(): ApiServiceToken {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiServiceToken::class.java)
    }

}