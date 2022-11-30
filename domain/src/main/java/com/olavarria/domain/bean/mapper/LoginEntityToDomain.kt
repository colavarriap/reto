package com.olavarria.domain.bean.mapper

import com.olavarria.data.api.response.*
import com.olavarria.domain.bean.*

internal fun UserResponse.toDomain(): User {
    return User(
        this._id,
        this.rbac?.toDomain(),
        this.profile?.toDomain()
    )
}

internal fun RBACResponse.toDomain(): RBAC {
    return RBAC(
        this.template,
        this.role
    )
}

internal fun ProfileResponse.toDomain(): Profile {
    return Profile(
        this.language,
        this.languages,
        this.countriesLogin?.map { it.toDomain() },
        this.countryLogin?.toDomain()
    )
}

internal fun CountryResponse.toDomain(): Country {
    return Country(
        this.code,
        this.name?.toDomain(),
        this.city?.toDomain(),
        this.date
    )
}

internal fun CityResponse.toDomain(): City {
    return City(
        this.name?.toDomain()
    )
}

internal fun NamesResponse.toDomain(): Names {
    return Names(
        this.it,
        this.de,
        this.zh,
        this.pt,
        this.es,
        this.fr,
        this.original
    )
}
