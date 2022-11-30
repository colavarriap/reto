package com.olavarria.data.api

import com.olavarria.data.BuildConfig

object ApiConstants {
    const val CONNECT_TIMEOUT = 20L
    const val READ_TIMEOUT = 120L
    const val WRITE_TIMEOUT = 120L

    const val BASE_URL = BuildConfig.BASE_URL

    const val AUTHORIZATION = "Authorization"
    const val AUTHORIZATION_TOKEN = "Basic cHJ1ZWJhc2RldjpwcnVlYmFzZGV2U2VjcmV0"
    const val UNAUTHORIZED = 401


}
