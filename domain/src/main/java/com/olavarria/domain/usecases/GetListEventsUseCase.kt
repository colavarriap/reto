package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import com.olavarria.data.repository.ApiRepository
import com.olavarria.data.repository.LocalRepository
import com.olavarria.domain.bean.SportEvents
import com.olavarria.domain.bean.mapper.toDomain
import com.olavarria.domain.bean.mapper.toLocal
import java.lang.IllegalStateException
import javax.inject.Inject


class GetListEventsUseCase @Inject constructor(
    private val apiRepository: ApiRepository,
    private val localRepository: LocalRepository
    ) : SingleUseCase<GetListEventsUseCase.GetListEventsParams, ApiState<SportEvents>>() {

    data class GetListEventsParams(val filters: Map<String, String>)

    override suspend fun run(params: GetListEventsParams): ApiState<SportEvents> {
        return when (val res = apiRepository.getSportEvents(params.filters)) {
            is ApiState.Success -> {
                if(res.data.body != null) {
                    localRepository.insertObject(res.data.body.items!!.map { it.toLocal() })
                    ApiState.Success(res.data.body.toDomain())
                } else
                    ApiState.ErrorCode("No existen los datos solicitados")
            }
            is ApiState.Error -> ApiState.Error(res.exception)
            is ApiState.ErrorCode -> ApiState.ErrorCode(res.message)
            else -> throw IllegalStateException("DOMAIN -> Ocurrio un error")
        }
    }

}