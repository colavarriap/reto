package com.olavarria.core.di.preferences

import com.olavarria.core.di.preferences.model.UserModel

interface Preferences {

    fun isSession(): Boolean
    fun setSession(status: Boolean)

    fun getToken(): String
    fun setToken(token: String)

    fun getUser(): UserModel?
    fun setUser(user: UserModel)

}