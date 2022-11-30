package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import com.olavarria.core.common.map
import com.olavarria.core.di.preferences.BasePreferenceManager
import com.olavarria.data.repository.ApiRepository
import com.olavarria.domain.bean.User
import com.olavarria.domain.bean.mapper.toDomain
import com.olavarria.domain.bean.mapper.toEntity
import com.olavarria.domain.bean.request.Device
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SignInFlowUseCase @Inject constructor(private val apiRepository: ApiRepository) : UseCase<SignInFlowUseCase.LoginParams, User>() {

    @Inject
    lateinit var preferenceManager: BasePreferenceManager

    data class LoginParams(val device: Device)

    override fun execute(params: LoginParams): Flow<ApiState<User>> {
        return apiRepository.validateLoginFlow(params.device.toEntity()).map {
            it.map { response ->
                preferenceManager.setToken(response.body.token)
                response.body.user.toDomain()
            }
        }
    }

}