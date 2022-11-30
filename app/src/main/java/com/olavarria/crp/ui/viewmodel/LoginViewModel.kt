package com.olavarria.crp.ui.viewmodel

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.olavarria.core.common.Status
import com.olavarria.core.di.preferences.Preferences
import com.olavarria.core.extensions.doOnStatusChanged
import com.olavarria.core.extensions.doOnStatusResult
import com.olavarria.core.extensions.doOnSuccess
import com.olavarria.core.extensions.doOnSuccessResult
import com.olavarria.crp.BuildConfig
import com.olavarria.crp.R
import com.olavarria.domain.bean.User
import com.olavarria.domain.usecases.SignInFlowUseCase
import com.olavarria.domain.usecases.SignInUseCase
import com.olavarria.crp.ui.base.CRPBaseViewModel
import com.olavarria.crp.ui.mapper.toModel
import com.olavarria.crp.ui.utils.getDeviceId
import com.olavarria.domain.bean.request.AppData
import com.olavarria.domain.bean.request.Device
import com.olavarria.domain.bean.request.DeviceData
import com.olavarria.domain.bean.request.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferences: Preferences,
    private val signInUseCase: SignInUseCase,
    private val signInFlowUseCase: SignInFlowUseCase
) : CRPBaseViewModel() {

    private val _loginResult : MutableStateFlow<User?> = MutableStateFlow(null)
    val loginResult: StateFlow<User?> get() = _loginResult

    fun validaLoginFlow(context: Context, name: String) {

        signInFlowUseCase(SignInFlowUseCase.LoginParams(device = getDatosDispositivo(context, name)))
            .doOnStatusChanged {
                initStatusState(it)
            }.doOnSuccess {
                saveUser(it)
                _loginResult.value = it
            }.launchIn(viewModelScope)

    }

    fun validaLogin(context: Context, name: String) {

        initStatusState(Status.Loading)
        signInUseCase(SignInUseCase.LoginParams(device = getDatosDispositivo(context, name)), viewModelScope)  {
            it.doOnSuccessResult { user ->
                saveUser(user)
                _loginResult.value = user
            }.doOnStatusResult { status ->
                initStatusState(status)
            }
        }

    }

    fun getDatosDispositivo(context: Context, name: String): Device {
        val deviceData = DeviceData (
            deviceId = getDeviceId(context),
            name = name,//android.os.Build.USER,
            version = android.os.Build.VERSION.SDK_INT.toString(),
            width = Resources.getSystem().displayMetrics.widthPixels.toString(),
            height = Resources.getSystem().displayMetrics.heightPixels.toString(),
            model = android.os.Build.MODEL,
            platform = context.getString(R.string.platform)
        )

        return Device(
            user = Profile(Locale.getDefault().language.toString()),
            device = deviceData,
            app = AppData(BuildConfig.VERSION_NAME)
        )
    }

    private fun saveUser(user: User) = preferences.setUser(user.toModel())



}