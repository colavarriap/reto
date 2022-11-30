package com.olavarria.crp.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window.FEATURE_NO_TITLE
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.crp.R
import com.olavarria.core.base.activity.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(FEATURE_NO_TITLE)

        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(3000)

            if (BasePreferenceManager(this@SplashActivity).isSession()) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }

            finish()
        }

    }


}