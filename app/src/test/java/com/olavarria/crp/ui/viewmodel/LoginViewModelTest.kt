package com.olavarria.crp.ui.viewmodel

import android.content.Context
import com.olavarria.core.common.ApiState
import com.olavarria.core.di.preferences.Preferences
import com.olavarria.domain.bean.User
import com.olavarria.domain.usecases.SignInFlowUseCase
import com.olavarria.domain.usecases.SignInUseCase
import com.olavarria.crp.ui.CoroutinesTestRule
import com.olavarria.domain.bean.request.AppData
import com.olavarria.domain.bean.request.Device
import com.olavarria.domain.bean.request.DeviceData
import com.olavarria.domain.bean.request.Profile
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {

    @RelaxedMockK
    private lateinit var preferences: Preferences

    @RelaxedMockK
    private lateinit var signInUseCase: SignInUseCase

    @RelaxedMockK
    private lateinit var  signInFlowUseCase: SignInFlowUseCase

    private lateinit var loginViewModel: LoginViewModel
    @RelaxedMockK
    private lateinit var context: Context

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(preferences, signInUseCase, signInFlowUseCase)
    }

    @Test
    fun `cuando se valida el login y se obtiene los datos del usuario`() = runTest {
        val user = User("123abc")

        /*every {
            signInFlowUseCase(SignInFlowUseCase.LoginParams(getDatosDispositivo("myPhone")))
        } returns flow { emit(ApiState.Success(user)) }*/

        var mock1 = mockk<SignInFlowUseCase>()
        Mockito.`when`(mock1.invoke(SignInFlowUseCase.LoginParams(getDatosDispositivo("myPhone"))))
            .then {
                    invocation -> (invocation.arguments[1] as (ApiState<User>) -> Unit).invoke(ApiState.Success(user))
            }

        loginViewModel.validaLogin(context, "myPhone")

        verify {
            assert(loginViewModel.loginResult.value == user)
        }

    }

    fun getDatosDispositivo(name: String): Device {
        val deviceData = DeviceData (
            deviceId = "123af13243dsf",
            name = name,
            version = "",
            width = "",
            height = "",
            model = "",
            platform = "android"
        )

        return Device(
            user = Profile("es"),
            device = deviceData,
            app = AppData("1.0.0")
        )
    }

}