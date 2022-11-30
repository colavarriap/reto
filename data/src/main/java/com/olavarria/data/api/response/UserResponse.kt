package com.olavarria.data.api.response

data class UserResponse (
    val _id: String,
    val rbac: RBACResponse? = null,
    val profile: ProfileResponse? = null,
)

data class RBACResponse (
    val template: String,
    val role: String
)

data class ProfileResponse (
    val language: String,
    val languages: String? = null,
    val countriesLogin: List<CountryResponse>? = null,
    val countryLogin: CountryResponse? = null,
)

data class CountryResponse (
    val code: String,
    val name: NamesResponse? = null,
    val city: CityResponse? = null,
    val date: String? = null
)

data class CityResponse(
    val name: NamesResponse? = null
)

data class NamesResponse (
    val it: String? = null,
    val de: String? = null,
    val zh: String? = null,
    val pt: String? = null,
    val es: String? = null,
    val fr: String? = null,
    val original: String? = null
)

