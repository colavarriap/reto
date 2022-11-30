package com.olavarria.crp.ui.base

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.olavarria.core.base.BaseViewModel
import com.olavarria.core.common.GeneralErrorsHandler
import com.olavarria.core.common.Status
import com.olavarria.crp.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class CRPBaseViewModel : BaseViewModel() {

    private val _baseEvent = MutableSharedFlow<CRPBaseViewEvent>()
    val baseEvent: SharedFlow<CRPBaseViewEvent> = _baseEvent

    fun initStatusState(
        status: Status,
        isShowLoading: Boolean = true,
        isShowErrorMessage: Boolean = true,
        errorId: Int? = null,
    ) {
        when (status) {
            is Status.Loading -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "LOADING")
                }
                showLoading(isShowLoading)
            }
            is Status.Error -> {
                if (BuildConfig.DEBUG) {
                    status.exception?.let {
                        Log.d("STATE ->", "ERROR ${it.message}")
                    }
                    status.errorMessage?.let {
                        Log.d("STATE ->", "ERROR $it")
                    }

                }
                dismissLoading(isShowLoading)

                status.exception?.let {
                    if (isShowErrorMessage) {
                        GeneralErrorsHandler(
                            { message, code ->
                                checkError(message, code, errorId)
                            }, it
                        )
                    }
                } ?:

                status.errorMessage?.let { checkError(it, 0, 0) }
            }
            is Status.Success -> {
                if (BuildConfig.DEBUG) {
                    Log.d("STATE ->", "SUCCESS")
                }
                dismissLoading(isShowLoading)
            }
        }
    }

    private fun dismissLoading(isShowLoading: Boolean) {
        if (isShowLoading) {
            dismissLoading()
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        if (isShowLoading) {
            showLoading()
        }
    }

    private fun checkError(message: Any, code: Int, errorId: Int?) {
        showErrorMessage(
            message, errorId
        )
    }

    private fun showLoading() {
        _baseEvent.emitSuspending(CRPBaseViewEvent.ShowLoading)
    }

    private fun dismissLoading() {
        _baseEvent.emitSuspending(CRPBaseViewEvent.DismissLoading)

    }

    private fun showErrorMessage(message: Any, errorId: Int? = null) {
        _baseEvent.emitSuspending(CRPBaseViewEvent.ShowErrorMessage(message, errorId))

    }

    /*fun showErrorLogin(tipo: Int) {
        _baseEvent.emitSuspending(CRPBaseViewEvent.ShowErrorMessage(tipo))
    }*/

    fun <T> MutableSharedFlow<T>.emitSuspending(value: T) =
        viewModelScope.launch(Dispatchers.IO) { emit(value) }


}