package com.olavarria.core.di.preferences

import android.content.Context
import com.olavarria.core.di.preferences.model.UserModel
import com.olavarria.core.extensions.get
import com.olavarria.core.extensions.set
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasePreferenceManager @Inject constructor(@ApplicationContext private val context: Context) : BaseSharedPreferences(context), Preferences {

    companion object {
        const val BASE_PREF = "BASE_PREF"
        const val KEY_SESSION = "KEY_SESSION"
        const val KEY_TOKEN = "KEY_TOKEN"
        const val KEY_USER = "KEY_USER"
    }

    override fun getPrefName() = BASE_PREF

    override fun isSession(): Boolean {
        return prefs.get<Boolean>(KEY_SESSION)?: false
    }

    override fun setSession(status: Boolean) {
        prefs.set(KEY_SESSION, status)
    }

    override fun getToken(): String {
        return prefs.get<String>(KEY_TOKEN)?: ""
    }

    override fun setToken(token: String) {
        prefs.set(KEY_TOKEN, token)
    }

    override fun getUser(): UserModel? {
        return prefs.get<UserModel>(KEY_USER)?: null
    }

    override fun setUser(user: UserModel) {
        prefs.set(KEY_USER, user)
    }


}