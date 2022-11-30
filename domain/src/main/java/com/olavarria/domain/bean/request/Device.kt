package com.olavarria.domain.bean.request

data class Device (
    val user: Profile,
    val device: DeviceData,
    val app: AppData
)

data class Profile (
    val language: String
)

data class DeviceData (
    var deviceId: String,
    var name: String,
    var version: String,
    var width: String,
    var height: String,
    var model: String,
    var platform: String
)

data class AppData (
    val version: String
)