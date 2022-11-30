package com.olavarria.domain.bean.mapper

import com.olavarria.data.api.request.AppDataRequest
import com.olavarria.data.api.request.DeviceDataRequest
import com.olavarria.data.api.request.DeviceRequest
import com.olavarria.data.api.response.ProfileResponse
import com.olavarria.domain.bean.request.AppData
import com.olavarria.domain.bean.request.Device
import com.olavarria.domain.bean.request.DeviceData
import com.olavarria.domain.bean.request.Profile

internal fun Device.toEntity(): DeviceRequest {
    return DeviceRequest(
        this.user.toEntity(),
        this.device.toEntity(),
        this.app.toEntity()
    )
}

internal fun Profile.toEntity(): ProfileResponse {
    return ProfileResponse(
        this.language
    )
}

internal fun DeviceData.toEntity(): DeviceDataRequest {
    return DeviceDataRequest(
        this.deviceId,
        this.name,
        this.version,
        this.width,
        this.height,
        this.model,
        this.platform
    )
}

internal fun AppData.toEntity(): AppDataRequest {
    return AppDataRequest(
        this.version
    )
}