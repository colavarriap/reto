package com.olavarria.domain.usecases

import android.content.res.Resources
import android.os.Build
import com.olavarria.core.common.ApiState
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.data.api.response.ApiResponse
import com.olavarria.data.api.response.LoginResponse
import com.olavarria.data.api.response.UserResponse
import com.olavarria.data.repository.ApiRepository
import com.olavarria.data.repository.LocalRepository
import com.olavarria.domain.CoroutinesTestRule
import com.olavarria.domain.bean.Pagination
import com.olavarria.domain.bean.SportEvents
import com.olavarria.domain.bean.mapper.toEntity
import com.olavarria.domain.bean.request.AppData
import com.olavarria.domain.bean.request.Device
import com.olavarria.domain.bean.request.DeviceData
import com.olavarria.domain.bean.request.Profile
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.*
import org.junit.*
import java.util.*

class SignInUseCaseTest {

    @RelaxedMockK
    private lateinit var apiRepository: ApiRepository

    private lateinit var signInUseCase: SignInUseCase

    private lateinit var preferenceManager: BasePreferenceManager

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        signInUseCase = SignInUseCase(apiRepository)

        preferenceManager = mockk<BasePreferenceManager>()
    }

    @Test
    fun `cuando el estado devuelve falso muestra un mensaje`() = coroutinesTestRule.scope.runTest {
        //Given
        coEvery {
            apiRepository.validateLogin(getDatosDispositivo().toEntity())
        } returns ApiState.ErrorCode("usuario no encontrado")
        //When
        signInUseCase(SignInUseCase.LoginParams(getDatosDispositivo()), this) {
            if (it is ApiState.ErrorCode)
                Assert.assertEquals("usuario no encontrado", it.message)
        }
        //Then
        /*coVerify {
            ApiState.ErrorCode("usuario no encontrado")
        }*/
    }

    @Test
    fun `cuando se obtiene el usuario exitosamente`() = runTest {

        val result = ApiResponse(LoginResponse("1112312", "12-01-01","Bearer", UserResponse("123abc")))

        coEvery {
            apiRepository.validateLogin(getDatosDispositivo().toEntity())
        } returns ApiState.Success(result)

        signInUseCase(SignInUseCase.LoginParams(getDatosDispositivo()), this) {
            if (it is ApiState.Success)
                Assert.assertEquals(result.body.user._id, it.data._id)
        }

    }

    fun getDatosDispositivo(): Device {
        val deviceData = DeviceData (
            deviceId = "123af13243dsf",
            name = android.os.Build.USER,
            version = Build.VERSION.SDK_INT.toString(),
            width = Resources.getSystem().displayMetrics.widthPixels.toString(),
            height = Resources.getSystem().displayMetrics.heightPixels.toString(),
            model = Build.MODEL,
            platform = "android"
        )

        return Device(
            user = Profile(Locale.getDefault().language.toString()),
            device = deviceData,
            app = AppData("1.0.0")
        )
    }

}