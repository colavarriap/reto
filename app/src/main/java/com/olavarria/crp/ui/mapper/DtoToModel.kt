package com.olavarria.crp.ui.mapper

import com.olavarria.core.di.preferences.model.UserModel
import com.olavarria.domain.bean.User


internal fun User.toModel(): UserModel {
    return UserModel(
        this._id,
        this.rbac?.role,
        this.profile?.language,
        this.profile?.countryLogin?.city?.name?.original,
        this.profile?.countryLogin?.code,
        this.profile?.countryLogin?.date
    )
}