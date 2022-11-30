package com.olavarria.data.api.request

import com.olavarria.data.api.response.ProfileResponse

data class DeviceRequest (
    val user: ProfileResponse,
    val device: DeviceDataRequest,
    val app: AppDataRequest
)

data class DeviceDataRequest (
    var deviceId: String,
    var name: String,
    var version: String,
    var width: String,
    var height: String,
    var model: String,
    var platform: String
)

data class AppDataRequest (
    val version: String
)