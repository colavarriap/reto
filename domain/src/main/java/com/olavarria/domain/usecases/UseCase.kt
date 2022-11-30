package com.olavarria.domain.usecases

import com.olavarria.core.common.ApiState
import kotlinx.coroutines.flow.Flow

abstract class UseCase<Request, Response> {
    operator fun invoke(params: Request): Flow<ApiState<Response>> = execute(params)

    protected abstract fun execute(params: Request): Flow<ApiState<Response>>
}
