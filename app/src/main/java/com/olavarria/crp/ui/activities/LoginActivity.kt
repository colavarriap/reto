package com.olavarria.crp.ui.activities

import android.content.Intent
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.crp.R
import com.olavarria.crp.databinding.ActivityLoginBinding
import com.olavarria.crp.extensions.collect
import com.olavarria.crp.extensions.observer
import com.olavarria.crp.ui.base.CRPBaseActivity
import com.olavarria.crp.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class LoginActivity : CRPBaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun getLayoutResourceId() = R.layout.activity_login

    @Inject
    lateinit var preferenceManager: BasePreferenceManager

    override fun onInitDataBinding() {
        with(viewBinding) {

            collect(viewModel.loginResult) { user ->
                user?.let {
                    preferenceManager.setSession(true)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }

            btnLogin.setOnClickListener {

                if(!edNameDevice.text.isNullOrEmpty()) {
                    viewModel.validaLoginFlow(this@LoginActivity, edNameDevice.text.toString())
                } else {
                    showSnackBar(getString(R.string.snackbar_edittext_error))
                }

                dismissKeyboard()
            }
        }
    }


}