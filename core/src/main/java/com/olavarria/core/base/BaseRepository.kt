package com.olavarria.core.base

import com.olavarria.core.BuildConfig
import com.olavarria.core.common.ApiState
import com.olavarria.core.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

abstract class BaseRepository {

    @Inject
    @IoDispatcher
    lateinit var dispatcher: CoroutineDispatcher

    fun <T : Any> onApiCall(call: suspend () -> T): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            emit(ApiState.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            recordException(error)
            emit(ApiState.Error(error))
        }.flowOn(dispatcher)

    fun <T : Any?> onApiFlowCall(call: suspend () -> T): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            while (true) {
                emit(ApiState.Success(data = call.invoke()))
                delay(10000)
            }
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            recordException(error)
            emit(ApiState.Error(error))
        }.flowOn(dispatcher)

    suspend fun <T: Any> onApiCallCoroutine(call: suspend () -> Response<T>): ApiState<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val result = response.body()

                if (result != null) return ApiState.Success(data = result)
            }
            return ApiState.ErrorCode("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            recordException(e)
            return ApiState.Error(e)
        }
    }

    private fun recordException(error: Throwable) {
        //algun log
    }

    fun <T : Any> onRoomCall(call: suspend () -> T): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            emit(ApiState.Success(data = call.invoke()))
        }.catch { error ->
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            recordException(error)
            emit(ApiState.Error(error))
        }.flowOn(dispatcher)

    fun <T : Any?> onRoomFlowCall(call: Flow<T>): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Loading)
            call.collect {
                emit(ApiState.Success(it))
            }
        }.catch {
            recordException(Throwable("onRoomFlowCall"))
            emit(ApiState.Error(it))
        }.flowOn(dispatcher)



}
