package com.olavarria.domain.usecases

import kotlinx.coroutines.*

abstract class SingleUseCase<in Params, out Type> where Type : Any {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Type) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(params)
            }
            onResult(deferred.await())

        }

    }

    class None
}
