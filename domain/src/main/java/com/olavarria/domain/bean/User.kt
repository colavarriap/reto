package com.olavarria.domain.bean

data class User (
    val _id: String,
    val rbac: RBAC? = null,
    val profile: Profile? = null,
)

data class RBAC (
    val template: String,
    val role: String
)

data class Profile (
    val language: String,
    val languages: String? = null,
    val countriesLogin: List<Country>? = null,
    val countryLogin: Country? = null,
)

data class Country (
    val code: String,
    val name: Names? = null,
    val city: City? = null,
    val date: String? = null
)

data class City(
    val name: Names? = null
)

data class Names (
    val it: String? = null,
    val de: String? = null,
    val zh: String? = null,
    val pt: String? = null,
    val es: String? = null,
    val fr: String? = null,
    val original: String? = null
)