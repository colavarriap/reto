package com.olavarria.crp.di

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.core.di.preferences.BasePreferenceManager.Companion.KEY_SESSION
import com.olavarria.crp.ui.activities.SplashActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    lateinit var preferences: SharedPreferences

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleEventObserver)
        preferences = getSharedPreferences(BasePreferenceManager(this).getPrefName(), Context.MODE_PRIVATE)
        registerListener()
    }

    private val listener = OnSharedPreferenceChangeListener { sharedPreferences, key ->
        if(key == KEY_SESSION) {
            if (!sharedPreferences.getBoolean(key, false)) {
                var intent = Intent(this, SplashActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    fun registerListener() = preferences.registerOnSharedPreferenceChangeListener(listener)

    fun unregisterListener() = preferences.unregisterOnSharedPreferenceChangeListener(listener)

    private val lifecycleEventObserver = LifecycleEventObserver { source, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                Log.i("TAG", "FOREGROUND: base")
                registerListener()
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.i("TAG", "BACKGROUND: base")
                unregisterListener()
            }
            Lifecycle.Event.ON_DESTROY-> {
                Log.i("TAG", "DESTROYED: base")
            }
            Lifecycle.Event.ON_CREATE -> {}
            Lifecycle.Event.ON_START -> {}
            Lifecycle.Event.ON_STOP -> {}
            Lifecycle.Event.ON_ANY -> {
                Log.i("TAG", "ANY: base")
            }
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }

}