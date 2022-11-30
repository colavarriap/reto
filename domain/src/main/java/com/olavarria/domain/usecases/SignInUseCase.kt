package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.data.repository.ApiRepository
import com.olavarria.domain.bean.User
import com.olavarria.domain.bean.mapper.toDomain
import com.olavarria.domain.bean.mapper.toEntity
import com.olavarria.domain.bean.request.Device
import java.lang.IllegalStateException
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val apiRepository: ApiRepository
    ) : SingleUseCase<SignInUseCase.LoginParams, ApiState<User>>() {

    @Inject
    lateinit var preferenceManager: BasePreferenceManager

    data class LoginParams(val device: Device)

    override suspend fun run(params: LoginParams): ApiState<User> {
        return when (val res = apiRepository.validateLogin(params.device.toEntity())) {
            is ApiState.Success -> {
                if(res.data.body != null) {
                    preferenceManager.setToken(res.data.body.token)
                    ApiState.Success(res.data.body.user.toDomain())
                } else
                    ApiState.ErrorCode("No existen los datos solicitados")
            }
            is ApiState.Error -> ApiState.Error(res.exception)
            is ApiState.ErrorCode -> ApiState.ErrorCode(res.message)
            else -> throw IllegalStateException("DOMAIN -> Ocurrio un error")
        }
    }

}